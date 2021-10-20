<template>
    <el-row class="header">
        <el-col :span="17">
            <img 
                :src="require('../assets/logo.png')" 
                alt="logo"
                class="logo"
                @click="$router.push({ name: '/login'})"
            />
        </el-col>
        <el-col :span="5" class="user">
            <img :src="require('../assets/exAvatar.jpg')" />
            <div class="infos">
                <p>Gabriel Fernandes</p>
                <p>Chefe de cozinha</p>
            </div>
        </el-col>
        <el-col class="butto" :span="2">
            <img @click="logout" :src="require('../assets/log_out.svg')">
        </el-col>
    </el-row>
</template>

<script>
import { deleteCookie } from "@/utils/Cookie";

export default {
    name: "Header",
    methods: {
        logout() {
            deleteCookie("token");
            this.$router.push("/");
        },
    },
};
</script>


<style lang="scss" scoped>
.header {
  background: #f88836;
  padding: 10px 0;

  .user {
    display: flex;
    flex-direction: row-reverse;
    align-items: center;
    img {
      width: 55px;
      border-radius: 50%;
      display: flex;
      flex-direction: column-reverse;
    }

    .infos {
      p {
        color: white;
        font-weight: 800;
        letter-spacing: 1px;
        &:nth-child(2) {
          font-weight: normal;
          font-size: 0.875rem;
        }
      }
    }
  }

  .button {
    height: 60px;
    display: flex;
    align-items: center;

    img {
      cursor: pointer;
      margin-left: auto;
      margin-right: 2rem;
      filter: brightness(0) invert(1);
      width: 40px;

      &:hover {
        filter: brightness(0) invert(0.85);
      }
    }
  }

  .logo {
    margin-left: 1rem;
    max-width: 115px;
    max-height: 101px;
    filter: brightness(0) invert(1);
    cursor: pointer;
    image-rendering: auto;
  }

  p {
    display: flex;
  }
}
</style>