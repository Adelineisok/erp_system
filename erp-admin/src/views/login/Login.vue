<template>
  <div class="login-container">

    <el-card class="login-card">

      <h2 class="title">
        小型ERP管理系统
      </h2>

      <el-form
          :model="loginForm"
          :rules="rules"
          ref="formRef"
      >

        <el-form-item prop="username">

          <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              clearable
          >
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>

        </el-form-item>

        <el-form-item prop="password">

          <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="请输入密码"
              show-password
          >
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>

        </el-form-item>

        <el-form-item>

          <el-button
              type="primary"
              style="width:100%"
              @click="login"
          >
            登录
          </el-button>

        </el-form-item>

      </el-form>

    </el-card>

  </div>
</template>

<script setup>

import {ref} from "vue";

import {useRouter} from "vue-router";

import {ElMessage} from "element-plus";

import {User, Lock} from "@element-plus/icons-vue";

import request from "../api/request";
import {login} from "../api/user";
const router = useRouter()

const formRef = ref()

const loginForm = ref({

  username: "",

  password: ""

})

const rules = {

  username: [

    {

      required: true,

      message: "请输入用户名",

      trigger: "blur"

    }

  ],

  password: [

    {

      required: true,

      message: "请输入密码",

      trigger: "blur"

    }

  ]

}

const login = () => {

  formRef.value.validate((valid) => {

    if (!valid) return

    request.post("/user/login", loginForm.value)

        .then(res => {

          if (res.code === 200) {

            ElMessage.success("登录成功")

            localStorage.setItem(

                "user",

                JSON.stringify(res.data)

            )

            router.push("/layout/dashboard")

          } else {

            ElMessage.error(res.msg)

          }

        })

        .catch(() => {

          ElMessage.error("服务器异常")

        })

  })

}

</script>

<style scoped>

.login-container{

  width:100vw;

  height:100vh;

  background:linear-gradient(135deg,#409EFF,#67C23A);

  display:flex;

  justify-content:center;

  align-items:center;

}

.login-card{

  width:420px;

  padding:20px;

  border-radius:12px;

}

.title{

  text-align:center;

  margin-bottom:30px;

}

</style>