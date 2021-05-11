<template>

  <div class="card-list-container"
       v-for="(list,i) in getLists"
       :key="list.id"
       @drop="onDrop($event,list)"
       @dragenter.prevent
       @dragover.prevent>

    <header class="card__list--title">
      {{ list.name }}
    </header>
    <div class="card__list--cards">
      <div class="drag_start" draggable="true" v-on:dragstart="startDrag($event,card)"
           v-show="card.boardColumn.name === list.name && card.archive === false" v-for="card in getCardsBoard"
           :key="card.id">
        <app-card v-bind:text="card.title" v-bind:id="card.id"></app-card>
      </div>
    </div>
    <div class="card__list--footer notext-decoration">
      <a href="#" v-if="listsRefs[i]" class="notext-decoration btn__add--card" v-on:click="addCard(i,list)">
        <svg class="symbol__plus">
          <use xlink:href="#plus"></use>
        </svg>
        <div class="notext-decoration add__card">Добавить карточку</div>
      </a>
      <div v-if="!listsRefs[i]" class="card__textarea">
        <textarea class="card__add--textarea" v-model="cardText" name="card__text" id="card__text" cols="30" rows="10"
                  placeholder="Введите название карточки"></textarea>
        <div class="action__card">
          <button class="card__add--btn" v-on:click="clickBtnCard(i,list)">Добавить карточку</button>
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
        <textarea class="card__add--textarea" v-model="textColumn" name="card__text" id="card__text--list" cols="30"
                  rows="10"
                  placeholder="Введите название колонки"></textarea>
      <div class="action__card">
        <button class="card__add--btn" v-on:click="addBoardColumn">Добавить колонку</button>
        <svg class="symbol__close--cardAdd" v-on:click="clickAddList">
          <use xlink:href="#close"></use>
        </svg>
      </div>
    </div>
  </div>
</template>

<script>
import AppCard from "@/components/Board/AppCard";
import store from "@/store";

export default {
  name: "AppCardList",
  components: {AppCard},
  data: () => ({
    listsRefs: [],
    cardText: '',
    max_id_list: -1,
    textColumn: '',
    lists: [
      {
        id: 1,
        name: ''
      },
    ],
    cards: [
      {
        id: 1,
        title: '',
        boardColumn: {
          name: ''
        },
      },
    ],
    max_id: -1,
    addColumn: {
      text: '',
      board: -1,
    },
    card: {
      title: '',
      board_column: -1,
      board: -1,
      author: -1,
    }
  }),
  computed: {
    activeCardList() {
      return this.$store.state.activeCardList
    },
    getLists() {
      return this.$store.getters['board/boardColumns']
    },
    getCardsBoard() {
      return this.$store.getters['board/cards']
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
      console.log(item)
    },
    onDrop(event, list) {
      const itemId = event.dataTransfer.getData('itemId')
      console.log(list)
      console.log(itemId)
      // const item = this.cards.find(item => `${item.id}` === itemId)

      fetch(`http://localhost:9000/api/v1/board/${list.id}/update_card/${itemId}`, {
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
              this.$store.dispatch('board/mountCards', this.$route.params.id)
              this.cards = this.$store.getters['board/cards']
            }
          })
    },
    addCard(i, list) {
      for (let j = 0; j < this.listsRefs.length; j++) {
        this.listsRefs[j] = true
      }
      this.listsRefs[i] = this.listsRefs[i] !== true;
    },
    closeCard(i) {
      this.listsRefs[i] = true
    },
    clickBtnCard(i, list) {
      // this.cards.push({
      //   id: this.max_id + 1,
      //   text: document.querySelector('#card__text').value,
      //   name: this.lists[i].name
      // })

      this.card.title = this.cardText;
      this.card.board = this.$route.params.id
      this.card.board_column = list.id
      this.card.author = this.$store.getters['auth/user'].email

      fetch('http://localhost:9000/api/v1/board/add_card', {
        method: 'post',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${store.getters['auth/token']}`
        },
        mode: "cors",
        body: JSON.stringify(this.card)
      }).then(response => response.json())
          .then(result => {
            console.log(result)
            if (result.status === 203) {
              this.$store.dispatch('board/mountCards', this.$route.params.id)
              this.cards = this.$store.getters['board/cards']
              document.querySelector('#card__text').value = ''
              this.listsRefs[i] = true
            }
          })
    },
    clickAddList() {
      this.$store.commit('activateCardList')
    },
    addBoardColumn() {
      this.addColumn.text = this.textColumn;
      this.addColumn.board = this.$route.params.id
      fetch(`http://localhost:9000/api/v1/board/add_column`, {
        method: 'post',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${store.getters['auth/token']}`
        },
        mode: "cors",
        body: JSON.stringify(this.addColumn)
      }).then(response => response.json())
          .then(result => {
            console.log(result)
            if (result.status === 203) {
              this.$store.dispatch('board/mountBoardColumns', this.$route.params.id)
              this.lists = this.$store.getters['board/boardColumns']
              document.querySelector('#card__text--list').value = ''
              this.$store.commit('activateCardList')
            }
          })
    }
  },
  mounted() {
    // this.lists.forEach(item => this.listsRefs.push(true))
    console.log(this.listsRefs)

    this.$store.dispatch('board/mountBoardColumns', this.$route.params.id)
    this.$store.dispatch('board/mountCards', this.$route.params.id)
    this.$store.getters['board/boardColumns'].forEach(
        item => this.listsRefs.push(true)
    )
    this.lists = this.$store.getters['board/boardColumns']
    this.cards = this.$store.getters['board/cards']
    // this.max_id = this.cards[this.cards.length - 1].id
    // this.max_id_list = this.lists[this.lists.length - 1].id
  }
}
</script>

<style scoped>

</style>