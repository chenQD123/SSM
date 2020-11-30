/*import axios from 'axios';
Vue.prototype.$axios = axios;*/

var vue = new Vue({
    el: "#app",
    data: {
        user: {id:"",username:"aaa",password:"",roleId:""},
        userList: []
    },
    methods: {
        queryAll: function () {
            var _this = this;
            axios.post("/user/queryAll").then(function (response) {
                _this.userList = response.data;
                console.log(_this.userList);
            }).catch(function (err) {
                console.log(err);
            });
        },
        queryById: function (userid) {
            var _this = this;
            axios.post("/vue_Demo1/user/findById.do", {
                params: {
                    id: userid
                }
            }).then(function (response) {
              _this.user = response.data;
                $('#myModal').modal("show");
            }).catch(function (err) {
            });

        },
        update: function (user) {
            var _this = this;
            axios.post("/vue_Demo1/user/updateById.do",_this.user).then(function (response) {
                _this.findAll();
            }).catch(function (err) {
            });
        }
    },
    created(){
        this.queryAll();
    }
});