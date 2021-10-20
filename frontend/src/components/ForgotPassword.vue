<template>
  <div class="wrapper">
    <div class="centralizado">
      <img :src="require('../assets/logo.png')" alt="Logo GT Chefes" />
      <div class="texto">
        <h2>Esqueceu sua senha?</h2>
        <p>Informe seu e-mail e aguarde!</p>
        <p>Vamos enviar as instruções para recuperar a sua senha.</p>
      </div>
      <el-form
        ref="esqueceu"
        :model="dados"
        :rules="rules"
        label-position="top"
        label-width="150px"
        hide-required-asterisk
        class="formulario"
      >
        <div class="campo">
          <el-form-item label="E-mail" prop="email">
            <el-input
              v-model="dados.email"
              id="email"
              placeholder="Digite seu e-mail"
            ></el-input>
          </el-form-item>
        </div>
        <el-button class="botao">Recuperar Senha</el-button>
        <router-link class="link" to="/">Retornar ao Login</router-link>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dados: {
        email: "",
      },

      rules: {
        email: [
          {
            required: true,
            message: "E-mail obrigatório",
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

  .formulario {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    margin: 0px;
  }

  .campo {
    width: 270px;
  }

  .botao {
    width: 65%;
    height: 45px;
    background-color: #f88836;
    color: white;
    margin: 10px;
  }
  
  .botao:hover {
    background-color: #ffe0ca;
    color: #f88836;
  }
}
</style>
