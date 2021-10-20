<template>
  <div class="wrapper">
    <div class="centralizado">
      <img :src="require('../assets/logo.png')" alt="Logo GT Chefes" />
      <el-form
        ref="cadastro"
        :model="dados"
        :rules="rules"
        label-position="top"
        label-width="150px"
        hide-required-asterisk
        class="formulario"
      >
        <div class="campo">
          <el-form-item label="Nome completo" prop="nome">
            <el-input v-model="dados.nome" id="nome"></el-input>
          </el-form-item>
          <el-form-item label="E-mail" prop="email">
            <el-input v-model="dados.email" id="email"></el-input>
          </el-form-item>
          <el-form-item label="Senha" prop="senha">
            <el-input
              show-password
              v-model="dados.senha"
              id="senha"
              type="password"
            ></el-input>
          </el-form-item>
          <el-button v-loading="loading" class="botao" @click="entra()"
            >Cadastrar</el-button
          >
          <div class="error">{{ error }}</div>
        </div>
        <div class="div-avatar">
          <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            <el-button class="botao-carregar">Clique para carregar</el-button>
          </el-upload>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "CadastroUsuario",
  data() {
    return {
      loading: false,
      error: "",
      dados: {
        nome: "",
        email: "",
        senha: "",
      },
      rules: {
        nome: [
          {
            required: true,
            message: "Nome obrigatório",
            trigger: "blur",
          },
        ],
        email: [
          {
            required: true,
            type: "email",
            message: "Email obrigatório",
            trigger: "blur",
          },
        ],
        senha: [
          {
            required: true,
            message: "Senha obrigatória",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("Sua foto deve estar em formato .JPEG !");
      }
      if (!isLt2M) {
        this.$message.error("Sua foto não pode exceder 2MB !");
      }
      return isJPG && isLt2M;
    }
  },
};
</script>

<style lang="scss" scoped>
.wrapper {
  background-image: url("../assets/background.png");
  background-repeat: no-repeat;
  background-position-x: center;
  background-size: 100vw 100vh;
  font-family: "Lexend", sans-serif;

  justify-content: center;
  display: flex;
  height: 100vh;
  align-items: center;
}
.centralizado {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 540px;

  img {
    padding-bottom: 90px;
    width: 325px;
    height: 137px;
  }

  .botao {
    width: 100%;
    height: 50px;
    background-color: #f88836;
    color: #ffe0ca;
    border: 0px;
  }

  .botao-carregar {
    width: 65%;
    height: 50px;
    background-color: #f88836;
    color: #ffe0ca;
    border: 0px;
  }

  .campo {
    width: 270px;
  }

  .div-avatar {
    width: 270px;
    margin: 30px;
    padding-bottom: 100px;
  }

  .botao:hover,
  .botao-carregar:hover {
    background-color: #ffe0ca;
    color: #f88836;
  }

  .formulario {
    display: flex;
    text-align: center;
    width: 100%;
    margin: 0px;

    ::v-deep .el-form-item__label {
      padding: 0;
      line-height: 14px;
    }

    .error {
      padding-top: 8px;
      color: red;
    }
    .avatar-uploader .el-upload {
      display: flex;
      flex-direction: column;
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #f88836;
    }
    .avatar-uploader-icon {
      font-size: 28px;
      color: #ffe0ca;
      width: 150px;
      height: 150px;
      line-height: 150px;
      text-align: center;
    }
    .avatar {
      width: 178px;
      height: 178px;
    }
  }
}
</style>
