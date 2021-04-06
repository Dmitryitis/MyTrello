import {createStore} from 'vuex'
import auth from './modules/auth'
import main from "@/store/modules/main";

export default createStore({
    state() {
        return {
            activeMenu: false,
            activeTeamModal: false,
            activeBoardModal:false,
        }
    },
    mutations: {
        clickMenu(state) {
            state.activeMenu = state.activeMenu !== true;
        },
        activeModal(state) {
            state.activeTeamModal = state.activeTeamModal !== true;
        },
        activeModalBoard(state){
            state.activeBoardModal = state.activeBoardModal !== true;
        }
    },
    actions: {},
    modules: {}
})
