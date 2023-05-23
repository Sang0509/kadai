<<<template>
  <el-container style="min-height: 100vh">
    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 20px">
          <span  style="cursor: pointer" @click="collapse"></span>
          <span><img src="../assets/logosunseer.png" alt="" style="width: 40px; position: relative; top: 10px; right: 10px"><b style="text-align: center">社員管理システム</b></span>
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

      <el-main>
        <div style="margin: 10px 0">
          <el-input style="width: 300px" placeholder="氏名を入力してください" suffix-icon="el-icon-search" v-model="username"></el-input>
          <el-input style="width: 300px" placeholder="特技を入力してください" suffix-icon="el-icon-search" v-model="hobby"></el-input>
          <el-button class="ml-5" type="primary" @click="load" plain>検索</el-button>
          <el-button class="ml-5" type="warning" @click="reset" plain>リセット</el-button>
        </div>



        <div style="margin: 10px 0">
          <el-button type="primary" @click="handleAdd" plain>新規登録<i class="el-icon-circle-plus"></i></el-button>








        </div>
        <el-button @click="clearFilter">全部リセット</el-button>
        <el-table :data="tableData" height="500px" border
                  ref="filterTable"
                  style="width: 100%"
                  :default-sort = "{prop: '[id,username,sex,management,birthday,email,phone,address,school,hobby]', order: 'descending'}"
        >

          <el-table-column type="selection" width="55" ></el-table-column>
          <el-table-column prop="id" label="ID" width="120" sortable></el-table-column>
          
          <el-table-column fixed prop="username" label="氏名" width="120" sortable></el-table-column>
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


          <el-table-column prop="birthday" label="生年月日" width="120" sortable
                          >
          </el-table-column>
          <el-table-column prop="email" label="メールアドレス" width="150" sortable></el-table-column>
          <el-table-column prop="phone" label="電話番号" width="130" sortable></el-table-column>
          <el-table-column prop="address" label="住所" width="120" sortable></el-table-column>
          <el-table-column prop="school" label="最終学歴" width="120"
                           column-key="school"
                           :filters="[{text: '大学', value: '大学'},{text: '大学院', value: '大学院'}]"
                           :filter-method="filterHandler">

          </el-table-column>
          <el-table-column prop="hobby" label="特技" width="120" sortable></el-table-column>
          <el-table-column fixed="right" label="操作"　width="200" align="center">
            <template slot-scope="scope">
              <el-button type="success" @click="handleEdit(scope.row)" plain>編集<i class="el-icon-edit"></i></el-button>
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
                <el-button type="danger" slot="reference" @click="del(scope.row)">削除<i class="el-icon-remove"></i></el-button>
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
    <el-button @click="Visible = false">キャンセル</el-button>
    <el-button type="primary" @click="delect()">削除する</el-button>
       </span>
        </el-dialog>

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
    <el-button type="primary" @click="submitForm('ruleForm')">確認</el-button>
    <el-button @click="resetForm('ruleForm')">リセット</el-button>
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
      Visible : false,
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
          { required: true, message: 'メールアドレスを入力してください', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '電話番号を入力してください', trigger: 'blur' },
          { min: 11, max: 11, message: '11桁でお願いいたします', trigger: 'blur' }

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
      form: {},
      dialogFormVisible: false,
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
    Login() {
      console.log("ログイン")
      this.$router.push('/Login')
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
    // open() {
    //   this.$confirm('削除データは復帰できません。よろしいですか?', '提示', {
    //     confirmButtonText: '削除する',
    //     cancelButtonText: 'キャンセル',
    //     type: 'warning'
    //   }).then(() => {
    //     this.$message({
    //       type: 'success',
    //       message: '削除しました!',
    //     });
    //   }).catch(() => {
    //     this.$message({
    //       type: 'info',
    //       message: 'キャンセルしました'
    //     });
    //   });
    // },


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
      fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize=" + this.pageSize +"&username="+this.username+"&hobby="+this.hobby)
          .then(res => res.json())
          .then(res => {
            console.log("AAAAAAAAA"+this.username+"BBBBBBB"+this.hobby)
            this.tableData = res.data
            this.total = res.total
          })
    },

    save() {
      fetch("http://localhost:9090/user/add",{
        method:"post",
      })
      .then(res =>res.json())
          .then(res=> {
           if (res) {
             this.$message.success("保存しました")
             this.dialogFormVisible = false
           }else {
             this.$message.error("失敗しました")
           }
          })

    },
    handleAdd() {
      this.dialogFormVisible = true
      this.load()

    },

    handleEdit() {
    this.form = row
      this.dialogFormVisible = true
    },
    reset() {
      this.username = ""
      this.hobby = ""

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
