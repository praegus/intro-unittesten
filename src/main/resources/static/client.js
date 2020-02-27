import VueRouter from 'vue-router';
import auth from './auth'

new Vue({
    el: '#app',
    data: {
      name: 'Bas Stoker',
      colleagues: null,
    },
    mounted () {
        axios
          .get('user/all')
          .then(response => (this.colleagues = response.data))
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
       }
    ,
     filters: {
            currencydecimal (value) {
                return value.toFixed(2)
            }
          },
  })