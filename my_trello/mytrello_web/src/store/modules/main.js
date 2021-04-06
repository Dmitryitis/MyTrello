export default {
    namespaced: true,
    state() {
        return {
            activeMenu: false,
        }
    },
    mutations: {
        clickMenu(state) {
            state.activeMenu = state.activeMenu !== true;
        }
    },
    actions: {},
    modules: {}
}