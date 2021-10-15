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
            <el-input v-model="dados.senha" id="senha"></el-input>
          </el-form-item>
          <el-button v-loading="loading" class="botao" @click="entra()"
            >Cadastrar</el-button
          >
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
  .formulario {
    display: flex;
    text-align: center;
    width: 100%;
    margin: 0px;

    ::v-deep .el-form-item__label {
      padding: 0;
      line-height: 14px;
    }
  }
}
</style>
