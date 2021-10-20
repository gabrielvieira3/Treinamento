<template>
  <div class="wrapper">
    <div class="centralizado">
      <img :src="require('../assets/logo.png')" alt="Logo GT Chefes" />
      <el-form
        ref="login"
        :model="dados"
        :rules="rules"
        label-position="top"
        label-width="150px"
        hide-required-asterisk
        class="formulario"
      >
        <div class="campo">
          <el-form-item label="E-mail" prop="email">
            <el-input v-model="dados.email" id="email"></el-input>
          </el-form-item>
          <el-form-item label="Senha" prop="senha">
            <el-input show-password v-model="dados.senha" id="senha"></el-input>
          </el-form-item>
        </div>
        <el-button v-loading="loading" class="botao"> Entrar </el-button>
        <div class="error">{{ erro }}</div>
        <p>
          Não tem uma conta?
          <router-link class="link" to="/cadastroUsuario"
            >Cadastre-se</router-link
          >
        </p>
        <router-link class="link" to="/forgotpassword"
          >Esqueci minha senha
        </router-link>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      error: "",
      dados: {
        email: "",
        senha: "",
      },
      rules: {
        email: [
          {
            required: true,
            type: "email",
            message: "E-mail obrigatório",
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
  width: 300px;
  img {
    padding-bottom: 80px;
    width: 275px;
    height: 137px;
  }

  .formulario {
    text-align: center;
    width: 100%;
    margin: 0px;

    ::v-deep .el-form-item__label {
      padding: 0;
      line-height: 14px;
    }

    p,
    a {
      margin-top: 8px;
      font-size: 0.875rem;
    }

    .error {
      padding-top: 8px;
      color: red;
    }
  }
}
.link {
  text-decoration: none;
}
.campo {
  text-align: left;
  font-family: Arial, Helvetica, sans-serif;
}
.botao:hover {
  background-color: #ffe0ca;
  color: #f88836;
}
.botao {
  width: 100%;
  height: 50px;
  background-color: #f88836;
  color: white;
  border: 0px;
  margin-top: 10px;
}
</style>
