<template>

  <div>

    <el-row :gutter="20">

      <el-col :span="6">

        <el-card>

          <div class="card">

            <div>

              商品数量

            </div>

            <h1>{{dashboard.productCount}}</h1>

          </div>

        </el-card>

      </el-col>

      <el-col :span="6">

        <el-card>

          <div class="card">

            <div>

              订单数量

            </div>

            <h1>{{dashboard.orderCount}}</h1>

          </div>

        </el-card>

      </el-col>

      <el-col :span="6">

        <el-card>

          <div class="card">

            <div>

              采购数量

            </div>

            <h1>{{dashboard.purchaseCount}}</h1>

          </div>

        </el-card>

      </el-col>

      <el-col :span="6">

        <el-card>

          <div class="card">

            <div>

              库存预警

            </div>

            <h1 style="color:red">

              {{dashboard.warningCount}}

            </h1>

          </div>

        </el-card>

      </el-col>

    </el-row>

    <el-row
        :gutter="20"
        style="margin-top:20px"
    >

      <el-col :span="12">

        <el-card>

          <div
              id="orderChart"
              style="height:400px"
          >

          </div>

        </el-card>

      </el-col>

      <el-col :span="12">

        <el-card>

          <div
              id="purchaseChart"
              style="height:400px"
          >

          </div>

        </el-card>

      </el-col>

    </el-row>

  </div>

</template>

<script setup>

import {onMounted,reactive} from "vue";

import * as echarts from "echarts";

import {getDashboard} from "../api/dashboard";

const dashboard=reactive({

  productCount:0,

  orderCount:0,

  purchaseCount:0,

  warningCount:0,

  orderData:[],

  purchaseData:[]

})

const drawOrder=()=>{

  const chart=echarts.init(

      document.getElementById("orderChart")

  )

  chart.setOption({

    title:{

      text:"近七天订单"

    },

    tooltip:{},

    xAxis:{

      data:["一","二","三","四","五","六","日"]

    },

    yAxis:{},

    series:[{

      type:"line",

      smooth:true,

      data:dashboard.orderData

    }]

  })

}

const drawPurchase=()=>{

  const chart=echarts.init(

      document.getElementById("purchaseChart")

  )

  chart.setOption({

    title:{

      text:"近七天采购"

    },

    tooltip:{},

    xAxis:{

      data:["一","二","三","四","五","六","日"]

    },

    yAxis:{},

    series:[{

      type:"bar",

      data:dashboard.purchaseData

    }]

  })

}

onMounted(()=>{

  getDashboard().then(res=>{

    Object.assign(

        dashboard,

        res.data

    )

    drawOrder()

    drawPurchase()

  })

})

</script>

<style scoped>

.card{

  text-align:center;

}

.card h1{

  margin-top:15px;

  color:#409EFF;

}

</style>