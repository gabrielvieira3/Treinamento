<template>
  <div>
    <Header />
    <el-row class="rowBreadcrumb">
      <el-col :span="24" :sm="12">
        <h1>Cadastrar receitas</h1>
      </el-col>
      <el-col :span="24" :sm="12">
        <el-row align="middle" justify="end">
          <el-breadcrumb
            separator-class="el-icon-caret-right"
            class="breadcrumb"
          >
            <el-breadcrum-item :to="{ path: '/' }">Home</el-breadcrum-item>
            <el-breadcrum-item :to="{ path: '/receitas' }"
              >Receitas</el-breadcrum-item
            >
            <el-breadcrum-item>Cadastrar</el-breadcrum-item>
          </el-breadcrumb>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
      <el-form
        hide-required-asterisk
        ref="dados"
        :model="dados"
        :rules="rules"
        label-position="top"
        label-width="150px"
        class="formulario"
      >
        <el-row>
          <el-col class="colCorpo" :span="24" :md="12">
            <el-row>
              <el-form-item label="Nome da receita" prop="nome">
                <el-input v-model="dados.nome" />
              </el-form-item>
              <el-col :span="24" :sm="10" :md="8">
                <el-form-item label="Tempo de preparo" prop="tempo">
                  <el-input v-model="dados.tempo" />
                </el-form-item>
              </el-col>
              <el-col class="padding-left" :span="24" :sm="14" :md="10">
                <el-form-item label="Rendimento" prop="rendimento">
                  <el-input type="number" v-model.number="dados.rendimento">
                    <el-select
                      class="SelectRendimento"
                      slot="append"
                      v-model="dados.unidadeRendimento"
                      placeholder="unidade(s)"
                    >
                      <el-option
                        v-for="r in rendimentos"
                        :key="r.value"
                        :label="r.label"
                        :value="r.value"
                      >
                      </el-option>
                    </el-select>
                  </el-input>
                </el-form-item>
              </el-col>

              <el-col :span="24">
                <el-row>
                  <el-form-item label="Categorias" prop="categorias">
                    <el-checkbox-group v-model="dados.categorias">
                      <el-col :span="6">
                        <el-checkbox :value="1" label="Salgado" name="type">
                        </el-checkbox>
                        <el-checkbox :value="2" label="Doce" name="type">
                        </el-checkbox>
                         <el-checkbox :value="3" label="Vegano" name="type">
                        </el-checkbox>
                         <el-checkbox :value="4" label="Salada" name="type">
                        </el-checkbox>
                        <el-checkbox :value="5" label="Sobremesa" name="type">
                        </el-checkbox>
                        <el-checkbox :value="6" label="Fit" name="type">
                        </el-checkbox>
                      </el-col>
                    </el-checkbox-group>
                  </el-form-item>
                </el-row>
              </el-col>

              <el-col>
                <h2>Ingredientes</h2>
              </el-col>
              <el-col>
                <el-form
                  hide-required-asterisk
                  ref="ingrediente"
                  :model="ingrediente"
                  :rules="regraIngrediente"
                  label-position="top"
                  label-width="150px"
                >
                  <div class="divIngrediente">
                    <el-col :span="24" :sm="10" :md="8">
                      <el-form-item label="Nome do ingrediente" prop="nome">
                        <el-input v-model="ingrediente.nome" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="24" :sm="8" :md="10" class="padding-left">
                      <el-form-item label="Quantidade" prop="quantidade">
                        <el-input
                          type="number"
                          v-model="ingrediente.quantidade"
                        >
                          <el-select
                            slot="append"
                            v-model="ingrediente.quantidadeUnidade"
                            placeholder="unidade(s)"
                            class="unSelect"
                          >
                            <el-option label="gramas" value="gramas">
                            </el-option>
                            <el-option label="ml" value="ml"></el-option>
                            <el-option label="kg" value="kg"></el-option>
                            <el-option label="xícara" value="xicara">
                            </el-option>
                          </el-select>
                        </el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6" class="padding-left">
                      <el-button
                        icone="el-icon-plus"
                        type="primary"
                        class="btnAddPasso"
                        @click="addIngrediente()"
                        >Adicionar</el-button
                      >
                    </el-col>
                  </div>
                </el-form>
              </el-col>
            </el-row>

            <el-form-item prop="ingredientes"></el-form-item>
            <el-table class="tabelaIngredientes" :data="dados.ingredientes">
              <el-table-column
                fixed
                prop="nome"
                label="Ingredientes"
                width="auto"
              >
              </el-table-column>
              <el-table-column prop="qnt" label="Quantidade" width="auto">
              </el-table-column>
              <el-table-column label="" width="30">
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    size="small"
                    class="remove"
                    icon="el-icon-delete-solid"
                    @click.native.prevent="
                      deleteRow(scope.$index, dados.ingredientes)
                    "
                  >
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>

          <el-col class="colCorpo" :span="24" :md="12">
            <el-row>
              <h3>Modo de preparo</h3>
              <el-form
                ref="passos"
                :model="passo"
                :rules="rulesPasso"
                label-position="top"
                label-width="150px"
                hide-required-asterisk
              >
                <el-row
                  style="display: flex; align-items: flex-end"
                  align="bottom"
                >
                  <el-col :span="18">
                    <el-form-item label="Passo:" prop="descricao">
                      <el-input
                        v-model="passo.descricao"
                        placeholder="Descrição"
                      >
                      </el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="1"></el-col>
                  <el-col :span="5">
                    <el-button
                      icon="el-icon-plus"
                      type="primary"
                      class="btnAddPasso"
                      @click="addPasso()"
                      >Adicionar</el-button
                    >
                  </el-col>
                </el-row>
              </el-form>

              <el-form-item prop="passos"></el-form-item>
              <el-table :data="dados.passos" class="tabelaIngredientes">
                <el-table-column type="index" width="30" />
                <el-table-column label="Descrição" prop="descricao" />
                <el-table-column width="35">
                  <template slot-scope="scope">
                    <el-button
                      type="text"
                      size="small"
                      class="remove"
                      icon="el-icon-delete-solid"
                      @click.native.prevent="
                        deleteRow(scope.$index, dados.passos)
                      "
                    >
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>

              <el-form-item label="Descrição" prop="descricao">
                <el-input v-model="dados.descricao" rows="5" type="textarea">
                </el-input>
              </el-form-item>
            </el-row>
          </el-col>
        </el-row>
      </el-form>
    </el-row>
    <el-row class="footerBtn">
      <el-button
        icon="el-icon-close"
        type="default"
        @click="$router.push({ name: '/receitas' })"
        >Cancelar</el-button
      >
      <el-button
        icon="el-icon-plus"
        type="primary"
        @click="handleSubmit('dados')"
        >Cadastrar</el-button
      >
    </el-row>
  </div>
</template>

<script>
import Header from "../components/Header.vue";

export default {
  name: "CreateRecipe",
  components: { Header },
  data() {
      return {
          rendimentos: [
              {
                  value: 1,
                  label: "Porção",
              },
          ],
          
          dados: {
              nome: null,
              tempo: null,
              rendimento: null,
              unidadeRendimento: 1,
              categorias: [],
              descricao: null,
              ingredientes: [],
              passos: [],
          },

          rules: {
              nome: [
                  {
                      required: true,
                      message: "Nome obrigatório",
                      trigger: "blur",
                  },
                  {
                      min: 3,
                      max: 20,
                      message: "Deve conter 3 á 20 caracteres",
                      trigger: "blur",
                  },
              ],
              descricao: [
                  {
                      required: true,
                      message: "Descrição obrigatória",
                      trigger: "blur"
                  },
              ],
              tempo: [
                  {
                      required: true,
                      message: "Tempo obrigatório",
                      trigger: "change",
                  },
              ],
              rendimento: [
                  {
                      required: true,
                      messagem: "Rendimento obrigatório",
                      trigger: "blur",
                  },
              ],
              categorias: [
                  {
                      required: true,
                      type: "array",
                      message: "Selecione pelo menos uma categoria",
                      trigger: "change",
                  },
              ],
              ingredientes: [
                  {
                      required: true,
                      type: "array",
                      message: "A receita deve ter pelo menos um ingrediente",
                      trigger: "change",
                  },
              ],
              passos: [
                  {
                      required: true,
                      type: "array",
                      message: "A receita deve ter pelo menos um passo",
                      trigger: "change",
                  },
              ],
          },
          
          passo: {
              descricao: "",
          },
          rulesPasso: {
              descricao: {
                  required: true,
                  message: "Descrição do passo é obrigatória",
              },
          },
          ingrediente: {
              nome: "",
              quantidade: "",
              quantidadeUnidade: "gramas",
          },
          regraIngrediente: {
              nome: {
                  required: true,
                  message: "Nome do ingrediente obrigatório",
              },
              quantidade: {
                  required: true,
                  message: "Quantidade é um campo obrigatório",
              },
          },
          tipoRefeicao: {
              Salgado: 1,
              Doce: 2,
              Vegano: 3,
              Salada: 4,
              Sobremesa: 5,
              Fit: 6,
          },
          unidades: {
              gramas: 1,
              kg: 2,
              ml: 3,
              xicara: 4,
          }
      };
  },
  methods: {

      deleteRow(index, rows) {
          rows.splice(index, 1);
      },

      addPasso() {
          const { descricao } = this.passo;

          this.$refs["passos"].validate((valid) => {
              if(valid) {
                  this.dados.passos.push({ descricao });
                  this.$refs["passos"].resetFields();
              } else {
                  console.error("Erro ao adicionar passo");
              }
          })
      },

      addIngrediente() {
         const { quantidade, nome, quantidadeUnidade } = this.ingrediente;
         const quant = `${quantidade} ${quantidadeUnidade}`;

         this.$refs["ingrediente"].validate((valid) => {
             if(valid) {
                 this.dados.ingredientes.push({
                     nome,
                     quantidade,
                     tipoUnidade: { id: this.unidades[quantidadeUnidade]},
                     quant,
                 });
                 this.$refs["ingrediente"].resetFields();
             } else {
                 console.error("Erro ao adicionar ingrediente");
             }
         })
      },

      resetForm(formName) {
          this.$refs[formName].resetFields();
      }
  }
};
</script>

<style lang="scss" scoped>
.rowBreadcrumb {
  height: 100px;
  margin: 0 2rem;
  > div {
    height: 100px;
    display: flex;
    align-items: center;
  }

  > div:nth-child(2) {
    justify-content: flex-end;
  }
}

.selectRendimento {
  width: 100px;
}

.tabelaIngredientes {
  ::v-deep thead {
    tr {
      th {
        border-top: 1px solid rgb(131, 131, 131);
        border-bottom: 1px solid rgb(189, 189, 189);
      }
    }
  }

  ::v-deep tbody {
    tr:nth-child(2n-1) {
      background: #fee9cf;
    }
  }
}

.colCorpo:nth-child(1) {
  padding-right: 10px;
}
.colCorpo:nth-child(2) {
  padding-left: 10px;
}

.padding-left {
  padding-left: 15px;
}

.user {
  display: flex;
  width: auto;
}

.btnAddPasso {
  padding: 12px 0px;
  width: 100%;
  margin-bottom: 22px;
}

.form {
  margin: 0 2rem;
}

.remove {
  color: #c53030;
}

.unSelect {
  width: 7.5rem;
  color: #db6e20;
}

.footerBtn {
  display: flex;
  justify-content: space-between;
  padding: 30px 2rem;

  &::v-deep.el-row::after {
    content: unset;
    clear: unset;
  }

  &::v-deep.el-row::before {
    content: unset;
    clear: unset;
  }
}

h1 {
  border-bottom: 4px solid #f88836;
}

h2,
h3 {
  margin-bottom: 1rem;
}

@media screen and (max-width: 992px) {
  h1 {
    font-size: 1.6rem;
    line-height: 1.8rem;
  }
}

@media screen and (max-width: 768px) {
  .padding-left {
    padding-left: 0px;

    & button {
      margin-bottom: 30px;
    }
  }

  .rowBreadcrumb {
    height: 100px;
    margin: 0 2rem;
    > div {
      height: 100px;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    > div:nth-child(2) {
      justify-content: center;
    }
  }
}
</style>
