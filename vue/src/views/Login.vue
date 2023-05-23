<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #FFF0F5; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>Login In</b></div>


      <center>
      <el-input size="medium" prefix-icon="el-icon-user" v-model="login.username"></el-input>
      </center>
      <center>
      <el-input size="medium" prefix-icon="el-icon-lock"  show-password v-model="login.password"></el-input>
      </center>
      <div style="margin: 10px 0; text-align: right" class="me-login-button">
        <center>
        <el-button  @click="Home()" type="info" round style="margin-top: 20px; padding: 12px 45px; font-family: Calibri" size="small" autocomplete="off">ログイン</el-button>
        </center>
<!--        <div class="button" @click="login" >ログイン</div>-->

      </div>
    </div>
  </div>
</template>

<script>
export default {
  // name: 'Login',
  data() {
    return {
      login: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          {required: true, message: '用户名不能为空'}//trigger: 'blur'
        ],
        password: [
          {required: true, message: '密码不能为空'},
          {min: 6, message: 'password must be at least 6 characters'}
        ]
      },
      flag:'false',
      bigChar:'false'
    }
  },
  methods: {
    // login(formName) {
    //   let that = this
    //
    //   this.$refs[formName].validate((valid) => {
    //     if (valid) {
    //       if(this.login.account.trim()!=""&&this.login.password.trim()!=""){
    //         let params =new URLSearchParams();
    //         params.append('account',this.login.account);
    //         params.append('password',this.login.password);
    //
    //         this.$axios.post('http://localhost:8080/user/login',params)
    //             .then(function (response) {
    //               console.log(response);
    //               if (response.data==='登录成功'){
    //                 // user:that.userForm.account
    //                 that.$store.commit('updateUserName',that.login.account)
    //                 that.$router.push({name:'Home'});
    //               }else if(response.data==='登录失败'){
    //                 alert("登录失败，用户名不存在或者密码错误")
    //               }
    //             })
    //             .catch(function (error) {
    //               console.log(error);
    //             });
    //       }
    //
    //     } else {
    //       if(this.login.account.trim()===""){
    //         this.$refs.account.style.borderColor="red";
    //       }else{
    //         this.$refs.account.style.borderColor="#797979";
    //       }
    //
    //       if(this.login.password.trim()===""){
    //         this.$refs.password.style.borderColor="red";
    //       }else{
    //         this.$refs.password.style.borderColor="#797979";
    //       }
    //       return false;
    //     }
    //   });
    //
    // },
    Home() {
      console.log("ログイン")
      this.$router.push('/Home')
    },
    show(){
      if(this.flag){
        this.$refs.eyes.src="../../static/img/eyes.png";
        this.$refs.password.setAttribute("type", "text");
      }else{
        this.$refs.eyes.src="../../static/img/eyesclosed.png";
        this.$refs.password.setAttribute("type", "password");
      }
      this.flag=!this.flag;
    },
  }
}

</script>








<style scoped="scoped">
 .wrapper {
   height: 100vh;
   background-image: linear-gradient(to bottom right, #FFC0CB, #4682B4);
   overflow: hidden;
   color: lightslategray;

 }
 .button{
   cursor: pointer;
   border-radius: 20px;
   border: 1px solid #4682B4;
   background: 		#4682B4;
   color: #fff;
   font-size: 16px;
   font-weight: bold;
   padding: 12px 45px;
   margin-top: 40px;
   letter-spacing: 40px;
   text-transform: uppercase;
   transition: transform 80ms ease-in;

 }
 input[type="text"] {
   width: 240px;
   text-align: left;
   background: transparent;
   border: none;
   border-bottom: 1px solid #fff;
   font-family: "Comic Sans MS", sans-serif;
   font-size: 14px;

   padding: 10px 0;
   transition: border 0.5s;
   outline: none;
   color: lightslategray;
 }

 input[type="password"] {
   width: 240px;
   text-align: left;
   background: transparent;
   border: none;
   border-bottom: 1px solid #fff;
   font-family: "Comic Sans MS", sans-serif;
   font-size: 14px;
   padding: 10px 0;
   transition: border 0.5s;
   outline: none;
   color: lightslategray;
 }

</style>

