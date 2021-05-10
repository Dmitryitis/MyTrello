const TOKEN_KEY = 'jwt-token'
export default {
    namespaced: true,
    state() {
        return {
            token: localStorage.getItem(TOKEN_KEY),
            user: {
                email: ''
            }
        }
    },
    mutations: {
        setToken(state, token) {
            state.token = token

            let base64Url = token.split('.')[1];
            let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            let jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));
            state.user.email = JSON.parse(jsonPayload).sub
            localStorage.setItem(TOKEN_KEY, token)
        },
        logout(state) {
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
        user(state) {
            return state.user
        },
        isAuthenticated(_, getters) {
            return !!getters.token
        },
    }

}