import store from "@/store";
import axios from "axios";

export default {
    namespaced: true,
    state() {
        return {
            activeMenu: false,
            teams: [],
            boards: [],
        }
    },
    mutations: {
        clickMenu(state) {
            state.activeMenu = state.activeMenu !== true;
        },
        getTeams(state) {
            fetch(`http://localhost:9000/api/v1/team/teams?email=${store.getters['auth/user'].email}`, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${store.getters['auth/token']}`
                },
                mode: "cors"
            }).then(response => response.json())
                .then(result => {
                    state.teams = result
                })
        },
        getBoards(state) {
            fetch(`http://localhost:9000/api/v1/board/boards?email=${store.getters['auth/user'].email}`, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${store.getters['auth/token']}`
                },
                mode: "cors"
            }).then(response => response.json())
                .then(result => {
                    state.boards = result
                    console.log(state.boards)
                })
        }
    },
    actions: {
        mountTeams({commit, dispatch}) {
            commit('getTeams')
        },
        mountBoards({commit, dispatch}) {
            commit('getBoards')
        }
    },
    modules: {},
    getters: {
        teams(state) {
            return state.teams
        },
        boards(state) {
            return state.boards
        }
    }
}