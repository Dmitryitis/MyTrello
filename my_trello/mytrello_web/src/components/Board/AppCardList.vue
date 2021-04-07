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
        <textarea class="card__add--textarea" name="card__text" id="card__text" cols="30" rows="10"></textarea>
        <div class="action__card">
          <button class="card__add--btn">Добавить карточку</button>
          <svg class="symbol__close--cardAdd" v-on:click="addCard(i)">
            <use xlink:href="#close"></use>
          </svg>
        </div>
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
    lists: [
      {
        id: 1,
        name: 'ListOne'
      },
      {
        id: 2,
        name: 'ListTwo',
      },
      {
        id: 3,
        name: 'ListThree'
      }
    ],
    cards: [
      {
        id: 1,
        text: 'gggg',
        name: 'ListOne'
      },
      {
        id: 2,
        text: 'dfsdf',
        name: 'ListOne'
      },

      {
        id: 3,
        text: 'dfsdfs',
        name: 'ListOne'
      },
      {
        id: 4,
        text: 'dfssfsdfdsdf',
        name: 'ListTwo'
      },
      {
        id: 5,
        text: 'fsdfs',
        name: 'ListThree'
      },
      {
        id: 6,
        text: 'fsdfs',
        name: 'ListThree'
      }, {
        id: 7,
        text: 'fsdfs',
        name: 'ListThree'
      }

    ]
  }),
  methods: {
    getCards(list) {
      return this.cards.filter(card => card.name === list.name)
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
      this.listsRefs[i] = this.listsRefs[i] !== true;
    }
  },
  mounted() {
    this.lists.forEach(item => this.listsRefs.push(true))
  }
}
</script>

<style scoped>

</style>