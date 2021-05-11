<template>

  <div class="card-list-container"
       v-for="(list,i) in lists"
       :key="list.id"
       @drop="onDrop($event,list)"
       @dragenter.prevent
       @dragover.prevent>

    <header class="card__list--title">
      {{ list.name }}
    </header>
    <div class="card__list--cards">
      <div class="drag_start" draggable="true" v-on:dragstart="startDrag($event,card)" v-for="card in getCards(list)"
           :key="card.id">
        <app-card v-bind:text="card.text" v-bind:id="card.id"></app-card>
      </div>
    </div>
    <div class="card__list--footer notext-decoration">
      <a href="#" v-if="listsRefs[i]" class="notext-decoration btn__add--card" v-on:click="addCard(i)">
        <svg class="symbol__plus">
          <use xlink:href="#plus"></use>
        </svg>
        <div class="notext-decoration add__card">Добавить карточку</div>
      </a>
      <div v-if="!listsRefs[i]" class="card__textarea">
        <textarea class="card__add--textarea" name="card__text" id="card__text" cols="30" rows="10"
                  placeholder="Введите название карточки"></textarea>
        <div class="action__card">
          <button class="card__add--btn" v-on:click="clickBtnCard(i)">Добавить карточку</button>
          <svg class="symbol__close--cardAdd" v-on:click="closeCard(i)">
            <use xlink:href="#close"></use>
          </svg>
        </div>
      </div>
    </div>
  </div>
  <div class="card-list-container--add">
    <button class="card__list--addList" v-on:click="clickAddList">Добавить еще одну колонку</button>
    <div v-if="activeCardList" class="card__textarea">
        <textarea class="card__add--textarea" name="card__text" id="card__text--list" cols="30" rows="10"
                  placeholder="Введите название карточки"></textarea>
      <div class="action__card">
        <button class="card__add--btn" v-on:click="addColumn">Добавить колонку</button>
        <svg class="symbol__close--cardAdd" v-on:click="clickAddList">
          <use xlink:href="#close"></use>
        </svg>
      </div>
    </div>
  </div>
</template>

<script>
import AppCard from "@/components/Board/AppCard";

export default {
  name: "AppCardList",
  components: {AppCard},
  data: () => ({
    listsRefs: [],
    max_id_list: -1,
    lists: [
      {
        id: 1,
        name: 'ListOne'
      },
    ],
    cards: [
      {
        id: 1,
        text: 'gggg',
        boardColumn: {
          name: 'ListOne'
        },
      },
    ],
    max_id: -1
  }),
  computed: {
    activeCardList() {
      return this.$store.state.activeCardList
    }
  },
  methods: {
    getCards(list) {
      return this.cards.filter(card => card.boardColumn.name === list.name)
    },
    startDrag(event, item) {
      event.dataTransfer.dropEffect = 'move'
      event.dataTransfer.effectAllowed = 'move'
      event.dataTransfer.setData('itemId', item.id)
    },
    onDrop(event, list) {
      const itemId = event.dataTransfer.getData('itemId')
      const item = this.cards.find(item => `${item.id}` === itemId)
      item.name = list.name
    },
    addCard(i) {
      for (let j = 0; j < this.listsRefs.length; j++) {
        this.listsRefs[j] = true
      }
      this.listsRefs[i] = this.listsRefs[i] !== true;
    },
    closeCard(i) {
      this.listsRefs[i] = true
    },
    clickBtnCard(i) {
      this.cards.push({
        id: this.max_id + 1,
        text: document.querySelector('#card__text').value,
        name: this.lists[i].name
      })
      document.querySelector('#card__text').value = ''
      this.listsRefs[i] = true
    },
    clickAddList() {
      this.$store.commit('activateCardList')
    },
    addColumn() {
      this.lists.push({
        id: this.max_id_list,
        name: document.querySelector('#card__text--list').value
      })
      document.querySelector('#card__text--list').value = ''
      this.$store.commit('activateCardList')
    }
  },
  mounted() {
    this.lists.forEach(item => this.listsRefs.push(true))
    console.log(this.listsRefs)
    this.max_id = this.cards[this.cards.length - 1].id
    this.max_id_list = this.lists[this.lists.length - 1].id

    this.$store.dispatch('board/mountBoardColumns', this.$route.params.id)
    this.$store.dispatch('board/mountCards', this.$route.params.id)
    this.$store.getters['board/boardColumns'].forEach(
        item => this.listsRefs.push(true)
    )
    this.lists = this.$store.getters['board/boardColumns']
    this.cards = this.$store.getters['board/cards']
  }
}
</script>

<style scoped>

</style>