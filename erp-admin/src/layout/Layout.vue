<script setup>
import Aside from '../components/Aside.vue'

import Header from '../components/Header.vue'
</script>

<template>
  <el-container style="height:100vh">

    <Aside/>

    <el-container>

      <Header/>

      <el-main>

        <router-view/>

      </el-main>

    </el-container>

  </el-container>
</template>

<style scoped>

</style>