const TOKEN_KEY = 'jwt-token'
export default {
    namespaced: true,
    state() {
        return {
            token: localStorage.getItem(TOKEN_KEY)
        }
    },
    mutations: {
        setToken(state, token) {
            console.log(123)
            state.token = token
            localStorage.setItem(TOKEN_KEY, token)
        },
        logout(state) {
            console.log(1)
            state.token = null
            localStorage.removeItem(TOKEN_KEY)
        }
    },
    actions: {
        async login({commit, dispatch}, payload) {
            commit('setToken', payload.token)
        }
    },
    getters: {
        token(state) {
            return state.token
        },
        isAuthenticated(_, getters) {
            return !!getters.token
        },
    }

}