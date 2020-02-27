export default {
  template: `
    <div :id="'colleague-' + id" class="colleague">
    <div>
        <div class="avatar" :id="'avatar-' + id"></div>
        <br/>
        <h2>{{ fullname }}</h2>
        <br/>
        <a style="color: #696969;" :href="'mailto:' + email">{{ email }}</a>
    </div>
     <br/>
     <section class="form">
       <div class="field">
         <div class="aanvragen">
           <flat-pickr v-model="dateRange" :config="config" input-class="md-input" size="40"></flat-pickr>
          <button style="float: right;" v-on:click="inplannen">Vakantie inplannen</button>
         </div>
         <div v-if="successfulReservation" style="background-color: #dcffcc; color: black">
            Vastgelegde vakantie: <b>{{ successfulReservation }} </b>
         </div>
          <div v-if="errorDuringReservation" style="background-color: #ffa8a8; color: black">
            {{ errorDuringReservation }}
          </div>
       </div>
     </section>
     <br/>
    </div>
  `,
mounted () {
    this.emailHash = YaMD5.hashStr(this.email.toLowerCase().trim());
    document.getElementById("avatar-" + this.id).style.cssText = "background-image: url('https://www.gravatar.com/avatar/" + this.emailHash + "?s=150&d=identicon'";

    // random backgroundcolor:
    var cssHSL = "hsl(" + 360 * Math.random() + ',' +
                     (25 + 70 * Math.random()) + '%,' +
                     (85 + 10 * Math.random()) + '%)';
                     document.getElementById("colleague-" + this.id).style.cssText = "background-color: " + cssHSL;

    // bij refresh ook vakantie tonen:
       if (this.reservation != undefined) {
        this.successfulReservation = "" + this.reservation.startDate + " - " + this.reservation.endDate;
       }
  },
  data() {
    return {
      emailHash: "",
      messageTo: "",
      messageFrom: "",
      dateRange: "",
      successfulReservation: "",
      errorDuringReservation: "",
      config: {
        mode: "range",
        locale: "nl",
        size: "30",
        allowInput: true
      },
    }
  },
  methods: {
    update: function(req, resp) {
        // werk debug window bij met request en response:
        this.$emit('debug', {messageTo: req, messageFrom: resp} );

        if (resp.result) {
            // Gelukt: werk UI bij:
            this.errorDuringReservation = "";
            this.successfulReservation = req.dateRange;
        } else {
            // Er was een conflict met een collega:
             this.successfulReservation = "";
            this.errorDuringReservation = "Je kan nog niet op vakantie, er is een conflict met je collega " + resp.blockingUser.firstname + " " + resp.blockingUser.lastname + ".";
        }
    },
    inplannen: function(event) {
          this.messageTo = {dateRange: this.dateRange};
          axios
              .post('/user/reserve/' + this.id, this.messageTo)
              .then(response => (this.update(this.messageTo, response.data)))
              .catch(error => console.log('Fout request: ' + error));
    }
  },
  props: ['id', 'fullname', 'email', 'debug', 'reservation']
}