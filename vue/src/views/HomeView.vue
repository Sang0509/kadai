<<<template>
  <el-container style="min-height: 100vh" class="wrappe">
    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 20px">
          <span  style="cursor: pointer" @click="collapse"></span>
          <span><img src="../assets/logosunseer.png" alt="" style="width: 40px; color: 	#FFF0F5; position: relative; top: 10px; right: 10px"><b style="text-align: center; color: 	#FFF0F5">社員管理システム</b></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <div>
            <el-avatar icon="el-icon-user-solid" size="small" style="margin-top: 15px"></el-avatar>
          </div>
<!--          <span class="el-dropdown-link">管理者</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>-->
          <el-dropdown-menu slot="dropdown" >
            <el-button style="font-size: 14px; padding: 10px 10px" @click="Sta()">データ分析</el-button>
            <el-button  style="font-size: 14px; padding: 10px 10px" @click="Login()">ログアウト</el-button>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>
      <el-button @click="drawer = true" v-model="direction" label="ttb" type="inf" plain style="color: 	#FFF0F5 ">
        絞り込み
      </el-button>
      <el-drawer
          title="我是标题"
          :visible.sync="drawer"
          :direction="direction"
          :with-header="false">
        <span>
           <div style="margin: 10px 0"  class="demo-input-size">
          <el-input style="width: 400px; line-height: 60px;  margin-left: 200px; margin-top: 20px" placeholder="氏名を入力してください"  prefix-icon="el-icon-user" v-model="username"></el-input>
          <el-input style="width: 400px; margin-left: 10px; margin-top: 20px" placeholder="特技を入力してください"  prefix-icon="el-icon-basketball" v-model="hobby"></el-input>
          <el-input style="width: 400px; margin-left: 10px; margin-top: 20px" placeholder="メールを入力してください"  prefix-icon="el-icon-s-open" v-model="email"></el-input>
          <el-input style="width: 400px; margin-left: 200px; margin-top: 20px" placeholder="電話番号を入力してください"  prefix-icon="el-icon-phone-outline" v-model="phone"></el-input>
          <el-input style="width: 400px; margin-left: 10px; margin-top: 20px" placeholder="住所を入力してください"  prefix-icon="el-icon-location" v-model="address"></el-input>
          <el-input style="width: 400px; margin-left: 10px; margin-top: 20px" placeholder="生年月日を入力してください"  prefix-icon="el-icon-cold-drink" v-model="birthday"></el-input>

          <el-button class="ml-5" type="pri"  prefix-icon="el-icon-user" style="margin-left: 700px; margin-top: 20px; color:	#4B0082 "  @click="load" plain>検  索<i class="el-icon-search" style="margin-left: 6px"></i></el-button>
          <el-button class="ml-5" type="warnin" prefix-icon="el-icon-user" style="margin-left: 20px; color:	#4B0082 ; margin-top: 20px" @click="reset" plain>リセット<i class="el-icon-refresh-right"></i></el-button>

        </div>

        </span>
      </el-drawer>



      <el-main style="height: 90vh">
<!--        <div style="margin: 10px 0">-->
<!--          <el-input style="width: 200px" placeholder="氏名を入力してください" suffix-icon="el-icon-user" v-model="username"></el-input>-->
<!--          <el-input style="width: 200px; margin-left: 2px" placeholder="特技を入力してください" suffix-icon="el-icon-basketball" v-model="hobby"></el-input>-->
<!--          <el-input style="width: 200px; margin-left: 2px" placeholder="メールを入力してください" suffix-icon="el-icon-s-open" v-model="email"></el-input>-->
<!--          <el-input style="width: 200px; margin-left: 2px" placeholder="電話番号を入力してください" suffix-icon="el-icon-phone-outline" v-model="phone"></el-input>-->
<!--          <el-input style="width: 200px; margin-left: 2px" placeholder="住所を入力してください" suffix-icon="el-icon-location" v-model="address"></el-input>-->
<!--          <el-input style="width: 200px; margin-left: 2px" placeholder="生年月日を入力してください" suffix-icon="el-icon-cold-drink" v-model="birthday"></el-input>-->
<!--          <el-button class="ml-5" type="primary" @click="load" plain>検索</el-button>-->
<!--          <el-button class="ml-5" type="warning" @click="reset" plain>リセット</el-button>-->
<!--        </div>-->



        <div style="margin: 10px 0">
          <el-button type="mary" style="margin-top: 1px; color: #4B0082"  @click="handleAdd">新 規 登 録<i class="el-icon-circle-plus" style="margin-left: 6px" ></i></el-button>


        <el-button type="warnin" style="margin-top: 2px; color: #4B0082 " @click="clearFilter" plain>選び出すリセット<i class="el-icon-refresh"></i></el-button>
        <el-button class="ml-5" type="warnin" style="margin-left: 20px; color:#4B0082; margin-bottom: 3px; margin-top: 2px" @click="reset" plain>絞り込みリセット<i class="el-icon-refresh-right"></i></el-button>
        </div>
        <el-table :data="tableData" height="500px" border
                  :header-cell-style="{background:'#FFE4E1',color:'#606266'}"

                  ref="filterTable"
                  style="width: 100%"
                  :default-sort = "{prop: '[id,username,sex,management,birthday,email,phone,address,school,hobby]', order: 'descending'}"
        >


          <el-table-column prop="id" label="ID" width="120" sortable></el-table-column>


          <el-table-column  prop="username" label="氏名" width="120" sortable></el-table-column>
          <el-table-column prop="sex" label="性別" width="120"
                           column-key="sex"
                           :filters="[{text: '男', value: '男'},{text: '女', value: '女'}]"
                           :filter-method="filterHandler"
                           sortable>
          </el-table-column>
          <el-table-column prop="management" label="部署" width="120" sortable
                           column-key="management"
                           :filters="[{text: '一部', value: '一部'},{text: '二部', value: '二部'},{text: '三部', value: '三部'},{text: '四部', value: '四部'}]"
                           :filter-method="filterHandler">

          </el-table-column>


          <el-table-column prop="birthday" label="生年月日" width="120"
                           :formatter="formatDate"
                           sortable>
          </el-table-column>
          <el-table-column prop="email" label="メールアドレス" width="200" sortable></el-table-column>
          <el-table-column prop="phone" label="電話番号" width="150" sortable></el-table-column>
          <el-table-column prop="address" label="住所" width="120" sortable></el-table-column>
          <el-table-column prop="school" label="最終学歴" width="120"
                           column-key="school"
                           :filters="[{text: '大学', value: '大学'},{text: '大学院', value: '大学院'}]"
                           :filter-method="filterHandler">

          </el-table-column>
          <el-table-column prop="hobby" label="特技" width="120" sortable></el-table-column>
          <el-table-column fixed="right" label="操作"　width="200" align="center">
            <template slot-scope="scope">
              <el-button type="succ" @click="handleEdit(scope.row)" style="color: #9F88FF" plain>編集<i class="el-icon-edit"></i></el-button>
<!--              <el-popconfirm-->
<!--                  class="ml-5"-->
<!--                  confirm-button-text='確認'-->
<!--                  cancel-button-text='キャンセル'-->
<!--                  icon="el-icon-info"-->
<!--                  icon-color="red"-->
<!--                  nzPopconfirmPlacement="top"-->
<!--                  title="削除よろしいですか"-->
<!--                  @confirm="delete(scope.row.id)"-->
<!--              >-->
                <el-button type="dan" slot="reference" @click="del(scope.row)" style="color: #FF3333 ">削除<i class="el-icon-remove"></i></el-button>
<!--              </el-popconfirm>-->
            </template>
          </el-table-column>
        </el-table>

        <el-dialog
            title="【提示】"
            :visible.sync="Visible"
            width="30%"
            :close-on-click-modal="false"
            center>
          <span>削除データは復帰できません。よろしいですか?</span>
          <span slot="footer" class="dialog-footer">
    <el-button type="cang" @click="Visible = false">キャンセル</el-button>
    <el-button type="dan"  style="color: #FF3333; margin-left: 5px" @click="delect()">削 除 す る</el-button>
       </span>
        </el-dialog>

<!--        新规登录弹窗画面-->

        <el-dialog title="新規登録" :visible.sync="dialogFormVisible">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"　size="small" class="demo-ruleForm; block" >
            <el-form-item label="氏名" prop="username">
              <el-input v-model="ruleForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="顔画像" prop="picture">
              <el-upload
                  action="#"
                  list-type="picture-card"
                  :auto-upload="false">
                <i slot="default" class="el-icon-plus"></i>
                <div slot="file" slot-scope="{file}">
                  <img
                      class="el-upload-list__item-thumbnail"
                      :src="file.url" alt=""
                  >
                  <span class="el-upload-list__item-actions">
        <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
        <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleDownload(file)"
        >
          <i class="el-icon-download"></i>
        </span>
        <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file)"
        >
          <i class="el-icon-delete"></i>
        </span>
      </span>
                </div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="">
              </el-dialog>
<!--              <el-input v-model="ruleForm.picture" autocomplete="off"></el-input>-->
            </el-form-item>
            <el-form-item label="性別">
              <el-select v-model="ruleForm.sex" placeholder="性別を選択してください">
                <el-option label="男性" value="男性"></el-option>
                <el-option label="女性" value="女性"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="部署">
              <el-select v-model="ruleForm.management" placeholder="部署を選択してください">
                <el-option label="一部" value="一部"></el-option>
                <el-option label="二部" value="二部"></el-option>
                <el-option label="三部" value="三部"></el-option>
                <el-option label="四部" value="四部"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="生年月日" prop="birthday" class="demonstration">
<!--              <el-input v-model="ruleForm.birthday" autocomplete="off"> </el-input>-->
              <el-date-picker
                  v-model="ruleForm.birthday"
                  type="date"
                  placeholder="選択してください">
              </el-date-picker>

            </el-form-item>
            <el-form-item label="メールアドレス" prop="email">
              <el-input v-model="ruleForm.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="電話番号" prop="phone">
              <el-input v-model="ruleForm.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="住所" >
              <el-input v-model="ruleForm.address" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="最終学歴">
              <el-select v-model="ruleForm.school" placeholder="学歴を選択してください">
                <el-option label="大学" value="大学"></el-option>
                <el-option label="大学院" value="大学院"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="特技">
              <el-input v-model="ruleForm.hobby" autocomplete="off"></el-input>
            </el-form-item>



          </el-form>
          <span slot="footer" class="dialog-footer" style="text-align: center">
    <el-button type="pr" @click="save" style="color: #F5FFFA ">確  認</el-button>
    <el-button type="warnin" style="color: #4B0082 " @click="dialogVisible==false" plain>リセット</el-button>
  </span>
        </el-dialog>

<!--        编辑弹窗页面-->

        <el-dialog title="编辑" :visible.sync="dialogFormVisible1">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="80px"　 size="small" class="demo-ruleForm; block" >
            <el-form-item label="氏名" prop="username">
              <el-input v-model="ruleForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="顔画像" prop="picture">
              <el-upload
                  action="#"
                  list-type="picture-card"
                  :auto-upload="false">
                <i slot="default" class="el-icon-plus"></i>
                <div slot="file" slot-scope="{file}">
                  <img
                      class="el-upload-list__item-thumbnail"
                      :src="file.url" alt=""
                  >
                  <span class="el-upload-list__item-actions">
        <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
        <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleDownload(file)"
        >
          <i class="el-icon-download"></i>
        </span>
        <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file)"
        >
          <i class="el-icon-delete"></i>
        </span>
      </span>
                </div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="">
              </el-dialog>
              <!--              <el-input v-model="ruleForm.picture" autocomplete="off"></el-input>-->
            </el-form-item>
            <el-form-item label="性別" prop="sex">
              <el-select v-model="ruleForm.sex" placeholder="性別を選択してください">
                <el-option label="男性" value="男性"></el-option>
                <el-option label="女性" value="女性"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="部署" prop="management">
              <el-select v-model="ruleForm.management" placeholder="部署を選択してください">
                <el-option label="一部" value="一部"></el-option>
                <el-option label="二部" value="二部"></el-option>
                <el-option label="三部" value="三部"></el-option>
                <el-option label="四部" value="四部"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="生年月日" prop="birthday" class="demonstration">
              <!--              <el-input v-model="ruleForm.birthday" autocomplete="off"> </el-input>-->
              <el-date-picker
                  v-model="ruleForm.birthday"
                  type="date"
                  placeholder="選択してください">
              </el-date-picker>

            </el-form-item>
            <el-form-item label="メールアドレス" prop="email">
              <el-input v-model="ruleForm.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="電話番号" prop="phone">
              <el-input v-model="ruleForm.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="住所" prop="address">
              <el-input v-model="ruleForm.address" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="最終学歴" prop="school">
              <el-select v-model="ruleForm.school" placeholder="学歴を選択してください">
                <el-option label="大学" value="大学"></el-option>
                <el-option label="大学院" value="大学院"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="特技">
              <el-input v-model="ruleForm.hobby" autocomplete="off"></el-input>
            </el-form-item>



          </el-form>
          <span slot="footer" class="dialog-footer" style="text-align: center">
    <el-button type="pr" @click="save" style="color: #F5FFFA ">確認</el-button>
    <el-button type="warning" style="color: #4B0082 "  @click="dialogVisible==false" plain>リセット</el-button>
  </span>
        </el-dialog>




        <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[8, 16, 24,32]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>

      </el-main>
    </el-container>
  </el-container>



</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },

  data(){
    var checkEmail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
      if (!value) {
        return callback(new Error('邮箱不能为空'))
      }
      setTimeout(() => {
        if (mailReg.test(value)) {
          callback()
        } else {
          callback(new Error('请输入正确的邮箱格式'))
        }
      }, 100)
    }
    return {


      ruleForm: {
        username: '',
        picture: '',
        sex: '',
        birthday: '',
        management: '',
        email: '',
        phone: '',
        address: '',
        school: '',
        form:{},
        hobby: '',

        table: false,
        show3: true,
        disabled: false
      },
      value1:'',
      dialogImageUrl: '',
      dialogVisible: false,
      dialogVisible1: false,
      Visible : false,
      drawer: false,
      direction: 'ttb',
      rules: {
        username: [
          { required: true, message: '氏名を入力してください', trigger: 'blur' }

        ],
        // picture: [
        //   { required: true, message: '添付ファイルを選択してください', trigger: 'blur' }
        // ],
        sex: [
          { required: true, message: '性別を選択してください', trigger: 'blur' }
        ],
        management: [
          { required: true, message: '部署を選択してください', trigger: 'blur' }
        ],
        birthday: [
          { type: 'date', required: true, message: '生年月日を選択してください', trigger: 'blur' }
        ],
        email: [
          { required: true,message: 'メールアドレスを入力してください', trigger: 'blur' },
          { validator: checkEmail,　message: '正しいメールアドレスを入力してください', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '電話番号を入力してください', trigger: 'blur' },
          { min: 11, max: 11, message: '"-"なしで11桁でお願いいたします', trigger: 'blur' }

        ],
        address: [
          { required: true, message: '住所を入力してください', trigger: 'blur' }
        ],
        school: [
          { required: true, message: '最終学歴を選択してください', trigger: 'blur' }
        ]
      },
    tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 8,
      username: "",
      birthday: "",
      hobby: "",
      email: "",
      phone: "",
      address: "",
      form: {},
      dialogFormVisible: false,
      dialogFormVisible1: false,
     collapseBtnClass: 'el-icon-s-fold',
     isCollapse: false,
     sidewidth: 200
   }
 },

  created() {//请求分页查询
    this.load()
  },
  methods:{
  collapse(){
    this.isCollapse= !this.isCollapse
    if(this.isCollapse){
      this.sidewidth = 80
    }
    },
    // dateFormat:function(date){
    //   return moment(date).format("YYYY-MM-DD")
    // },

    formatDate(row, column) {
      // 获取单元格数据

      // 日期正常显示的方法

      let data = row[column.property]
      if(data == null) {
        return null
      }
      let dt = new Date(data)
      return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate()




          // + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
    },

    Login() {
      console.log("ログイン")
      this.$router.push('/Login')
      // 清除缓存
      history.pushState(null, null, document.URL);
      window.addEventListener("popstate",function(e) {
        history.pushState(null, null, document.URL);
      }, false);
    },
    Sta() {
      console.log("ログイン")
      this.$router.push('/Sta')
    },
    del(row){
      this.Visible = true,
      this.from = JSON.parse(JSON.stringify(row));
      console.log(this.from+"SADASDASDAD")
      },

    add(row){
      this.Visible = true,
          this.from = JSON.parse(JSON.stringify(row));
      console.log(this.from+"rererererer")
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    delect(){
      fetch("http://localhost:9090/user/del/"+this.from.id)
          .then(res => res.json())
          .then(res => {
            this.Visible = false
            console.log(this.form.id+"AAASCASCA")
            this.load();
          })
    },

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    formatter(row, column) {
        return row.address;
    },
    load() {
      fetch("http://localhost:9090/user/page?pageNum="+this.pageNum
          +"&pageSize=" + this.pageSize
          +"&username="+this.username
          +"&hobby="+this.hobby
          +"&birthday="+this.birthday
          +"&email="+this.email
          +"&phone="+this.phone
          +"&address="+this.address)
          .then(res => res.json())
          .then(res => {
            console.log("AAAAAAAAA"+this.username)
            console.log("BBBBBB"+this.hobby)
            console.log("CCCCCC"+this.birthday)
            console.log("DDDDDD"+this.email)
            console.log("EEEEEE"+this.address)
            console.log("FFFFFFF"+this.phone)
            this.tableData = res.data
            this.total = res.total
          })
    },

    save() {
      fetch("http://localhost:9090/user/add/",{
        method:"post",
        headers:{
          "Content-Type":"application/json"
        },
        body:JSON.stringify({
          id:this.ruleForm.id,
          username:this.ruleForm.username,
          picture:this.ruleForm.picture,
          sex:this.ruleForm.sex,
          management:this.ruleForm.management,
          email:this.ruleForm.email,
          phone:this.ruleForm.phone,
          address:this.ruleForm.address,
          school:this.ruleForm.school,
          birthday:this.ruleForm.birthday,
          hobby:this.ruleForm.hobby
        }),
      })
          .then(res =>res.json())
          .then(res=> {
           if (res) {
             this.$message.success("保存しました")
             this.dialogFormVisible = false
             // this.dialogFormVisible1 = false
             this.load()
           }else {
             this.$message.error("失敗しました")
           }
          })

    },
    handleAdd() {
      this.dialogFormVisible = true
      this.ruleForm={};
    },

    handleEdit() {
    // this.form = row
      this.dialogFormVisible1 = true
      this.ruleForm={};
    },
    reset() {
      this.username = ""
      this.hobby = ""
      this.birthday  = ""
      this.email  = ""
      this.address  = ""
      this.phone  = ""

      this.load()
    },
    handleSizeChange(pageSize) {
      console.log("pageSize"+pageSize)
      this.pageSize = pageSize
      this.load()

    },

    handleCurrentChange(pageNum) {
      console.log("pageNum"+pageNum)
      this.pageNum = pageNum
      this.load()

    }
  }
}
</script>
<style scoped="scoped">
/*背景渐变色*/
.wrappe {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FFC0CB, #4682B4);
  overflow: hidden;
  color: lightslategray;

}
/*编辑按钮颜色*/

.el-button--succ,.el-button--succ:focus,.el-button--succ.is-active, .el-button--succ:active{background:linear-gradient(to right,#FFFAFA ,#E6E6FA);}

.el-button--succ:hover {
  background: #8cd8da;
  border-color: #8cd8da;
  color: #FFF;
}
/*消除按钮颜色*/

.el-button--dan,.el-button--dan:focus,.el-button--dan.is-active, .el-button--dan:active{background:linear-gradient(to right,	#FDF5E6 ,#FFCCCC);}

.el-button--dan:hover {
  background: #8cd8da;
  border-color: #8cd8da;
  color: 	#FF8888;
}
/*新规登录按钮颜色*/

.el-button--mary,.el-button--mary:focus,.el-button--mary.is-active, .el-button--mary:active{background:linear-gradient(to right,#F5FFFA , #CCCCFF);}

.el-button--mary:hover {
  background: #8cd8da;
  border-color: #8cd8da;
  color: #FFF;
}
/*重置按钮颜色*/

.el-button--warnin,.el-button--warnin:focus,.el-button--warnin.is-active, .el-button--warnin:active{background:linear-gradient(to right,#FFE4E1,#F5FFFA );}

.el-button--warnin:hover {
  background: #8cd8da;
  border-color: #8cd8da;
  color: #FFF;
}
/*.el-button--info,.el-button--info:focus,.el-button--info.is-active, .el-button--info:active{background:linear-gradient(to right, #FFC0CB, #4682B4)}*/

/*.el-button--info:hover {*/
/*  background: #8cd8da;*/
/*  border-color: #8cd8da;*/
/*  color: #FFF;*/
/*}*/
/*絞り込み按钮的颜色*/

.el-button--inf,.el-button--inf:focus,.el-button--inf.is-active, .el-button--inf:active{background:linear-gradient(to right,#FFC0CB, #4682B4);}

.el-button--inf:hover {
  background: #8cd8da;
  border-color: #8cd8da;
  color: #FFF;
}
/*搜索按钮颜色*/
.el-button--pri,.el-button--pri:focus,.el-button--pri.is-active, .el-button--pri:active{background:	linear-gradient(to right,	#E6E6FA, #FFB6C1);}

.el-button--pri:hover {
  background: #8cd8da;
  border-color: #8cd8da;
  color: 	#FFF5EE ;
}
/*新规和编辑弹窗的按钮颜色*/
.el-button--pr,.el-button--pr:focus,.el-button--pr.is-active, .el-button--pr:active{background:linear-gradient(to right,	#E6E6FA, #FFB6C1);}

.el-button--pr:hover {
  background: #8cd8da;
  border-color: #8cd8da;
  color: 	#FFF5EE ;
}
/*取消按钮颜色*/
.el-button--cang,.el-button--cang:focus,.el-button--cang.is-active, .el-button--cang:active{background:linear-gradient(to right,	#E6E6FA, #F5FFFA );}

.el-button--cang:hover {
  background: #8cd8da;
  border-color: #8cd8da;
  color: 	#FFF5EE ;
}

</style>