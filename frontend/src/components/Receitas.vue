<template>
  <div class="geral">
    <Header />
    <el-dialog :title="visualize.nome" :visible.sync="dialogFormVisible">
      <div class="item descricao">
        <h3>Descrição:</h3>
        <span>{{ visualize.descricao }}</span>
      </div>

      <div class="item">
        <h3>Tempo de preparo:</h3>
        {{ visualize.tempoDePreparo }}
      </div>

      <div class="item">
        <h3>Rendimento:</h3>
        {{ visualize.rendimento }} porções
      </div>

      <div class="item">
        <h3>Categorias</h3>
        {{ getCategory(visualize.categorias) }}
      </div>

      <div class="item list">
        <h3>Ingredientes:</h3>
        <ul>
          <li
            v-for="ingrediente in visualize.ingredientes"
            :key="ingrediente.id"
          >
            <span>
              {{ ingrediente.quantidade }}
              {{ ingrediente.tipoUnidade.nome }} de {{ ingrediente.nome }}
            </span>
          </li>
        </ul>
      </div>

      <div class="item list">
        <h3>Modo de preparo:</h3>
        <ol>
          <li v-for="modo in visualize.modoPreparo" :key="modo.id">
            <span>
              {{ modo.nome }}
            </span>
          </li>
        </ol>
      </div>
    </el-dialog>
    <el-row class="rowBreadcrumb">
      <el-col :span="24" :sm="12">
        <h1>Receitas</h1>
      </el-col>
      <el-col :span="24" :sm="12">
        <el-row align="middle" justify="end">
          <el-breadcrumb
            separator-class="el-icon-caret-right"
            class="breadcrumb"
          >
            <el-breadcrumb-item :to="{ path: '/' }">Home</el-breadcrumb-item>
            <el-breadcrumb-item>Receitas</el-breadcrumb-item>
          </el-breadcrumb>
        </el-row>
      </el-col>
    </el-row>
    <el-row v-loading="loading" class="corpo">
      <div class="pesquisar">
        <div>
          <el-input v-model="pesquisa" placeholder="pesquisar" />
          <el-button @click="getSearch" type="primary" icon="el-icon-search">
            Pesquisar</el-button
          >
        </div>
        <el-button
          icon="el-icon-plus"
          type="primary"
          @click="$router.push({ name: '/createrecipe' })"
          >Cadastrar Receita</el-button
        >
      </div>
      <h2>Receitas</h2>
      <div class="tabela">
        <el-table
          @sort-change="getSort"
          class="tabelaReceitas"
          height="100%"
          :data="tableData"
        >
          <el-table-column
            sortable="custom"
            label="Nome da Receita"
            prop="nome"
          />
          <el-table-column
            sortable="custom"
            label="Tempo de Preparo"
            prop="tempoDePreparo"
          />
          <el-table-column sortalbe="custom" label="Categoria">
            <template slot-scope="scope">
              {{ getCategory(scope.row.categorias) }}
            </template>
          </el-table-column>
          <el-table-column sortable="custom" label="Chef" prop="chef" />
          <el-table-column
            sortable="custom"
            label="Rendimento"
            prop="rendimento"
          />
          <el-table-column label="Ações" width="130">
            <template slot-scope="scope">
              <el-dropdown>
                <el-button size="mini" type="primary"
                  >Ações <i class="el-icon-arrow-down el-icon--right"
                /></el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item
                    icon="el-icon-view"
                    @click.native.prevent="visualizar(scope.$index, scope.row)"
                  >
                    Visualizar</el-dropdown-item
                  >
                  <el-dropdown-item icon="el-icon-edit-outline" command="edit">
                    Editar</el-dropdown-item
                  >
                  <el-dropdown-item
                    icon="el-icon-close"
                    @click.native.prevent="
                      handleDelete(scope.$index, scope.row)
                    "
                  >
                    Remover</el-dropdown-item
                  >
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination">
          <el-pagination
            background
            small
            layout="prev, pager, next"
            :total="total"
            :page-size="size"
            :current-page="page + 1"
            @current-change="getData"
          >
          </el-pagination>
      </div>
    </el-row>
  </div>
</template>

<script>
import Header from "../components/Header.vue";

export default {
  name: "Receitas",
  components: {
    Header,
  },
  data() {
    return {
      loading: true,
      tableData: [],
      categoria: [],
      visualize: {},
      pesquisa: null,
      dialogFormVisible: false,
      size: 9,
      page: 0,
      total: null,
      field: "nome",
      order: "adc",
    };
  },

  mounted() {
    this.getData();
  },

  methods: {
    getCategory(cats) {
      let category = "";
      try {
        cats.map((cat) => {
          category += cat.name + ", ";
        });

        category = category.slice(0, -2);
      } catch (error) {
        console.log(error);
      }

      return category;
    },
    getSearch() {
      this.page = 0;
      this.getData();
    },
    // getData(event) {
    //     this.page = Number.isInteger(event) ? event - 1 : this.page;
    //     this.loading = true;

    //     getReceitas(this.search, this.page, this.size, this.field, this.order)
    //         .then((res) => {
    //             const { data } = res;
    //             this.total = data.totalItems;
    //             this.tableData = data.receitas;
    //         })
    //         .catch((e) => {
    //             console.log(e);
    //         })
    //         .finally(() => {
    //             this.loading = false;
    //         });
    // }
  },
};
</script>

<style lang="scss" scoped>
.geral {
  height: 100vh;
  display: flex;
  flex-direction: column;
  font-family: 'Lexend', sans-serif;

  .rowBreadcrumb {
    height: 100px;
    margin: 0 2rem;

    h1 {
      border-bottom: 4px solid #f88836;
    }

    > div {
      height: 100px;
      display: flex;
      align-items: center;
    }

    > div:nth-child(2) {
      justify-content: flex-end;
    }
  }

  .corpo {
    flex: 1;
    margin: 0 2rem;
    display: flex;
    flex-direction: column;
    max-height: 70%;
    overflow: hidden;

    .pesquisar {
      display: flex;
      justify-content: space-between;

      div:nth-child(1) {
        display: flex;
        flex: 0.65;
        max-width: 800px;

        button {
          margin-left: 10px;
        }
      }
    }

    h2 {
      margin-top: 25px;
    }

    .tabela {
      flex: 1;
      overflow: auto;

      .tabelaReceitas {
        position: relative;

        .icone {
          font-size: 1.5rem;
          cursor: pointer;

          &.el-icon-star-on {
            color: #f88836;
          }

          &.el-icon-star-off {
            font-size: 1.2rem;
            padding-left: 3px;
          }
        }

        ::v-deep thead {
          tr {
            th {
              // position: relative;
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
    }

    .pagination {
      margin: 15px 0;
      display: flex;
      justify-content: flex-end;
    }

    &.load {
    }
  }

  ::v-deep .el-dialog__wrapper {
    .el-dialog {
      margin: 2rem auto !important;
      width: 70%;

      .el-dialog__header {
        border-bottom: 4px solid #f88836;
        font-weight: bolder;
        .el-dialog__title {
          font-size: 1.75rem;
          color: #535353;
        }

        button {
          top: 12px;
          i {
            color: #535353;
            font-weight: bolder;
            font-size: 2rem;
          }
        }
      }

      .el-dialog__body {
        padding-top: 20px;

        .item {
          display: flex;
          font-size: 1.125rem;
          margin-top: 15px;

          h3 {
            font-size: 1.125rem;
            margin-right: 10px;
          }

          &:nth-child(1) {
            display: inherit;
            margin-top: 0;
            margin-bottom: 30px;
            h3 {
              margin-bottom: 15px;
            }
          }

          &.list {
            flex-direction: column;
            ul {
              margin-top: 5px;
              padding-left: 45px;

              li span {
                position: relative;
                left: -5px;
              }
            }

            ol {
              margin-top: 5px;
              padding-left: 25px;

              li {
                &::marker {
                  font-weight: bolder;
                  font-size: 1.25rem;
                }

                span {
                  padding-left: 10px;
                }
              }
            }
          }
        }
      }
    }
  }
}
</style>