<template>
  <div class="card__content--window" v-bind:class="{'active__card--content':activeCard}">
    <div class="card__content">
      <header class="card__content--header">
        <svg class="symbol__monitor">
          <use xlink:href="#monitor"></use>
        </svg>
        <div class="card__content--infoCard">
          <span class="card__content--title">{{ card.title }}</span>
          <span class="card__content--subtitle">в колонке
            <a href="#" class="notext-decoration">{{ card.boardColumn.name }}</a>
          </span>
        </div>

        <svg class="symbol__close--content" v-on:click="closeCard">
          <use xlink:href="#close"></use>
        </svg>
      </header>

      <div class="card__content--content">
        <div class="card__window--content">
          <div class="card__attachments--title">
            <svg class="symbol__attachments symbol__about">
              <use xlink:href="#about"></use>
            </svg>
            <span class="card__attachments--text">Описание</span>
            <button class="card__about--btn">Изменить</button>
          </div>

          <div class="card__about">
            <div class="card__about--text">
              {{ card.description }}
            </div>
            <textarea id="card__about--text" v-model="description.text" placeholder="Введите описание"
                      class="card__about--field" cols="30"
                      rows="10"></textarea>
            <span class="card__about--action">
            <button class="card__about--save" type="button" v-on:click="clickDescription">Сохранить</button>
              <svg class="symbol__close--about">
                <use xlink:href="#close"></use>
              </svg>
            </span>
          </div>

          <div class="card__attachments--title">
            <svg class="symbol__attachments">
              <use xlink:href="#attachments"></use>
            </svg>
            <span class="card__attachments--text">Вложения</span>
          </div>

          <div class="card__attachments--items">
            <div class="card__attachments--item">
              <div class="card__attachments--icon">SVG</div>
              <div class="card__attachments--info">
                <span class="card__attachments--name">iidid.svg</span>
                <div class="card__attachments--time">
                  Добавлено 09.04.2021 14:02
                </div>
                <a href="#" class="card__attachments--delete">Удалить</a>
              </div>
            </div>

            <button class="card__attachments--add">
              Добавить вложение
            </button>
          </div>

          <div class="card__attachments--title">
            <svg class="symbol__attachments">
              <use xlink:href="#checkList"></use>
            </svg>
            <span class="card__attachments--text">Чек-лист</span>
            <button class="card__about--btn">Удалить</button>
          </div>

          <div class="card__about">
            <div class="card__check--items">
              <div class="card__check--item">
                <input class="card__check--input" type="checkbox" id="dffff" value="dffff">
                <label for="dffff" class="card__check--label">dffff</label>
              </div>

              <div class="card__check--item">
                <input class="card__check--input" type="checkbox" id="dffff" value="dffff">
                <label for="dffff" class="card__check--label">dffff</label>
              </div>

              <button class="card__check--add">
                Добавить элемент
              </button>

              <textarea id="card__check--text" placeholder="Введите элемент" class="card__check--field" cols="30"
                        rows="10">
              </textarea>
              <span class="card__about--action">
              <button class="card__about--save">Сохранить</button>
              <svg class="symbol__close--about">
                <use xlink:href="#close"></use>
              </svg>
            </span>
            </div>
          </div>

          <div class="card__attachments--title">
            <svg class="symbol__attachments">
              <use xlink:href="#chat"></use>
            </svg>
            <span class="card__attachments--text">Чат</span>
          </div>

          <div class="card__chat">
            <div class="card__chat--field">
              <div class="card__chat--avatar">
                RM
              </div>
              <div class="card__chat--comment">
                <textarea class="card__chat--textarea" placeholder="Напишите комментарий" id="card__chat" cols="30"
                          rows="10"></textarea>
                <span class="card__about--action card__chat--action">
                  <button class="card__about--save">Сохранить</button>
              <svg class="symbol__close--about">
                <use xlink:href="#alias"></use>
              </svg>
            </span>
              </div>
            </div>
            <div class="card__comments">
              <div class="card__comment">
                <div class="card__comment--descr">
                  <div class="card__chat--avatar card__comment--avatar">
                    RM
                  </div>
                  <div class="card__comment--info">
                    <span class="card__comment--username">Дмитрий Герасимов</span>
                    <span class="card__comment--date">24.06.2021 15:49</span>
                  </div>
                </div>
                <div class="card__comment--text">
                  fsfsdf
                </div>
                <div class="card__comment--action">
                  <a href="#" class="card__comment--link notext-decoration">Изменить</a>
                  <a href="#" class="card__comment--link notext-decoration">Удалить</a>
                </div>
              </div>

            </div>
          </div>

        </div>
        <div class="card__window--sidebar">
          <div class="card__sidebar--title">Добавить на карточку</div>
          <div class="card__sidebar--item">Участники</div>
          <div class="card__sidebar--item">Чек-лист</div>
          <div class="card__sidebar--item">Срок</div>
          <div class="card__sidebar--item">Вложение</div>
          <div class="card__sidebar--item" v-on:click="clickArchive">Архивация</div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import router from "@/router";
import store from "@/store";

export default {
  name: "AppCardContent",
  data: () => ({
    description: {
      text: ''
    }
  }),
  computed: {
    activeCard() {
      return this.$store.state.activeCard
    },
    card() {
      return this.$store.getters['board/curCard']
    }
  },
  methods: {
    closeCard() {
      router.push({name: "Board", params: {id: this.$store.state.boardId}})
      this.$store.commit('activateCard')
    },
    clickArchive() {
      fetch(`http://localhost:9000/api/v1/board/archive_card/${this.$route.params.id}`, {
        method: 'put',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${store.getters['auth/token']}`
        },
        mode: "cors",
      }).then(response => response.json())
          .then(result => {
            console.log(result)
            if (result.status === 200) {
              this.$store.dispatch('board/mountCards', this.$store.state.boardId)
              router.push({name: "Board", params: {id: this.$store.state.boardId}})
              this.$store.commit('activateCard')
            }
          })
    },
    clickDescription() {
      if (this.description.text !== '') {
        fetch(`http://localhost:9000/api/v1/board/description_card/${this.$route.params.id}`, {
          method: "put",
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${store.getters['auth/token']}`
          },
          mode: "cors",
          body: this.description.text
        }).then(response => response.json())
            .then(result => {
              console.log(result)
              if (result.status === 203) {
                this.description.text = ''
                this.$store.commit('board/currentCard', this.$route.params.id);
              }
            })
      }
    }
  }
}
</script>

<style scoped>

</style>