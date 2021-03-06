import store from "@/store";

export default {
    namespaced: true,
    state() {
        return {
            board_id: -1,
            board: {
                id: '',
                name: '',
                team: {
                    name: ''
                }
            },
            boardMembers: [],
            boardColumns: [],
            allUsers: [],
            cards: [],
            curCard: {
                id: '',
                name: '',
                title: '',
                description: "",
                boardColumn: {
                    name: ''
                }
            }
        }
    },
    mutations: {
        getBoard(state, id) {
            fetch(`http://localhost:9000/api/v1/board/${id}/board`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${store.getters['auth/token']}`
                },
                mode: "cors"
            }).then(response => response.json())
                .then(result => {
                    console.log(result)
                    state.board = result
                })
        },
        getBoardMembers(state, id) {
            fetch(`http://localhost:9000/api/v1/board/${id}/members`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${store.getters['auth/token']}`
                },
                mode: "cors"
            }).then(response => response.json())
                .then(result => {
                    console.log(result)
                    state.boardMembers = result
                })
        },
        getBoardColumns(state, id) {
            fetch(`http://localhost:9000/api/v1/board/${id}/columns`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${store.getters['auth/token']}`
                },
                mode: "cors"
            }).then(response => response.json())
                .then(result => {
                    console.log(result)
                    state.boardColumns = result
                })
        },
        getAllUser(state) {
            fetch('http://localhost:9000/api/v1/board/users', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${store.getters['auth/token']}`
                },
                mode: "cors"
            }).then(response => response.json())
                .then(result => {
                    state.allUsers = result
                    console.log(result)
                })
        },
        getBoardCard(state, id) {
            fetch(`http://localhost:9000/api/v1/board/${id}/cards`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${store.getters['auth/token']}`
                },
                mode: "cors"
            }).then(response => response.json())
                .then(result => {
                    console.log(result)
                    state.cards = result
                })
        },
        currentCard(state, id) {
            fetch(`http://localhost:9000/api/v1/board/card/${id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${store.getters['auth/token']}`
                },
                mode: "cors"
            }).then(response => response.json())
                .then(result => {
                    console.log(result)
                    state.curCard = result;
                })
        }
    },
    actions: {
        mountBoard({commit, dispatch}, id) {
            commit('getBoard', id)
        },
        mountBoardMembers({commit, dispatch}, id) {
            commit('getBoardMembers', id)
        },
        mountBoardColumns({commit, dispatch}, id) {
            commit('getBoardColumns', id)
        },
        mountCards({commit, dispatch}, id) {
            commit('getBoardCard', id)
        }
    },
    getters: {
        board(state) {
            return state.board;
        },
        boardMembers(state) {
            return state.boardMembers;
        },
        boardColumns(state) {
            return state.boardColumns;
        },
        allUsers(state) {
            return state.allUsers;
        },
        cards(state) {
            return state.cards;
        },
        curCard(state) {
            return state.curCard
        }
    }
}