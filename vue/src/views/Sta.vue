<template>
  <div class="content bg">
<!--    全屏容器-->
  <dv-full-screen-container>
<!--    第一行-->
    <div class="module-box">
      <div style="flex: 0 1 30%">
        <dv-decoration-10 style="height:5px" />
      </div>
      <div style="flex: 0 1 40%">
        <div class="d-flex">
<!--          表头三等分-->
<!--          左-->
          <dv-decoration-8 style="height:30px; flex: 1;" />
<!--          中-->
<!--          <div style="flex: 1;" class="tc box-middle">Sunseer Data Center</div>-->
          <dv-decoration-11 style="width:200px;height:40px;" :color="['red', 'blue']">
          Sunseer Data Center
          </dv-decoration-11>
<!--          右-->
          <dv-decoration-8 :reverse="true" style="height:30px; flex: 1;" />
        </div>

      </div>
      <div style="flex: 0 1 30%">
        <dv-decoration-10 style="height:5px; transform: rotateY(180deg)" />

      </div>
    </div>




<!--    第二行-->
    <div class="module-box">
      <div style="flex: 0 1 25%">
<!--        左一-->
        <dv-border-box-1 style="width: 100%; height: 180px">
          <dv-capsule-chart :config="config1" style="width:100%;height:200px" />
        </dv-border-box-1>
<!--        左二-->
        <dv-border-box-8 style="width: 100%; height: 180px"></dv-border-box-8>
      </div>
      <div style="flex: 0 1 40%">
<!--        中间最大，用来放飞线图-->
        <dv-border-box-8 style="width: 100%; height: 360px">
          <dv-flyline-chart :config="config3" style="width:100%;height:100%;" />
        </dv-border-box-8>
      </div>
      <div style="flex: 0 1 35%">
<!--        右一-->
        <dv-border-box-12 style="width: 100%; height: 360px">
          <dv-capsule-chart :config="config2" style="width:100%;height:360px" />

        </dv-border-box-12>
<!--        <dv-border-box-8 style="width: 100%; height: 200px"></dv-border-box-8>-->
      </div>
    </div>

<!--    第三行-->
    <div class="module-box">
      <div style="flex: 0 1 25%">
<!--        左下-->
        <dv-border-box-1 style="width: 100%; height: 150px"></dv-border-box-1>
      </div>
      <div style="flex: 0 1 25%">
<!--        中下-->
        <dv-border-box-8 style="width: 100%; height: 150px"></dv-border-box-8>
      </div>
      <div style="flex: 0 1 25%">
        <!--        中下-->
        <dv-border-box-8 style="width: 100%; height: 150px"></dv-border-box-8>
      </div>
      <div style="flex: 0 1 25%">

<!--        右下-->
        <dv-border-box-12 style="width: 100%; height: 150px"></dv-border-box-12>
      </div>
    </div>





  </dv-full-screen-container>
  </div>

</template>

<script>
export default {
  data() {
    return {
      config1:{
        data: [
          {
            name: '南阳',
            value: 167
          },
          {
            name: '周口',
            value: 123
          },
          {
            name: '漯河',
            value: 98
          },
          {
            name: '郑州',
            value: 75
          },
          {
            name: '西峡',
            value: 66
          },
        ],
        colors: ['#e062ae', '#fb7293', '#e690d1', '#32c5e9', '#96bfff'],
        unit: '单位',
        showValue: true
      },
      config2:{
        data: [
          {
            name: '基本情報技術者',
            value: 5
          },
          {
            name: '応用情報技術者',
            value: 4
          },
          {
            name: 'Java　Sliver',
            value: 10
          },
          {
            name: 'OSS-DB Silver',
            value: 2
          },
          {
            name: 'ＩＴパスポート試験',
            value: 8
          },
          {
            name: 'TOEIC　600点以上',
            value: 8
          },
          {
            name: 'TOEIC　600点以上',
            value: 5
          },
          {
            name: 'ORACLE MASTER Silver DBA',
            value: 3
          },

        ],
        unit: '人',
        showValue: true
      },
      config3:{
        centerPoint: [0.48, 0.35],
        points: [
          {
            position: [0.52, 0.235],
            text: '新乡'
          },
          {
            position: [0.43, 0.29],
            text: '焦作'
          },
          {
            position: [0.59, 0.35],
            text: '开封'
          },
          {
            position: [0.53, 0.47],
            text: '许昌'
          },

        ],
        bgImgUrl: require('../assets/img/japanmap1.png')
      }
      }
    }

 }



</script>

<style scoped>

</style>