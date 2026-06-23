<script setup>

</script>

<template>

  <el-aside width="220px">

    <el-menu

        router

        background-color="#304156"

        text-color="#fff"

        active-text-color="#ffd04b"

    >

      <el-menu-item index="/layout/dashboard">

        首页

      </el-menu-item>

      <el-menu-item index="/layout/product">

        商品管理

      </el-menu-item>

      <el-menu-item index="/layout/warehouse">

        仓库管理

      </el-menu-item>

      <el-menu-item index="/layout/stock">

        库存管理

      </el-menu-item>

      <el-menu-item index="/layout/purchase">

        采购管理

      </el-menu-item>

      <el-menu-item index="/layout/order">

        订单管理

      </el-menu-item>

      <el-menu-item index="/layout/finance">

        财务管理

      </el-menu-item>

      <el-menu-item index="/layout/afterSale">

        售后管理

      </el-menu-item>

    </el-menu>

  </el-aside>

</template>

<style scoped>

</style>