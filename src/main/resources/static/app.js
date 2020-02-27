import ColleagueComponent from './ColleagueComponent.js';

new Vue({
  el: '#app',
  data: {
    messageTo: "",
    messageFrom: "",
    name: 'Bas Stoker',
    colleagues: null
  },
  components: {
    ColleagueComponent
  },
  mounted () {
      axios
        .get('/user/all')
        .then(response => (this.colleagues = response.data))
    },
  methods: {
    updateDebugString(message) {
        this.messageTo = message.messageTo;
        this.messageFrom = message.messageFrom;
    }
  },
  computed: {
        everyoneExceptMe: function () {
            if (this.colleagues != null && this.colleagues !== undefined) {
                return this.colleagues.filter(
                  function (c) {
                      return !(c.firstname + ' ' + c.lastname === name)
                  }
                )
            } else {
              return this.colleagues
            }
        }
     },
      filters: {
        currencydecimal (value) {
            return value.toFixed(2)
        }
      }
});
Vue.component('flat-pickr', VueFlatpickr);