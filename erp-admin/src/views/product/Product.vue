<script setup>
import {onMounted,ref }from'vue'

import {getProductList }from'../../api/product'

const tableData=ref([])

const loadData=async()=>{

  const res=awaitgetProductList()

  tableData.value=res.data.data

}

onMounted(()=>{

  loadData()

})

</script>

<template>
  <el-card>

    <h2>商品管理</h2>

    <el-table
        :data="tableData"
        border
    >

      <el-table-column
          prop="productCode"
          label="商品编码"
      />

      <el-table-column
          prop="productName"
          label="商品名称"
      />

      <el-table-column
          prop="salePrice"
          label="销售价"
      />

      <el-table-column
          prop="status"
          label="状态"
      />

    </el-table>

  </el-card>

</template>

<style scoped>

</style>