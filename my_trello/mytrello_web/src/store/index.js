import {createStore} from 'vuex'
import auth from './modules/auth'
import main from "@/store/modules/main";

export default createStore({
    state() {
        return {
            activeMenu: false,
            activeTeamModal: false,
            activeBoardModal: false,
            activePopup: false,
            activeInvitation: false,
            activeArchive: false,
            activeCardList: false,
        }
    },
    mutations: {
        clickMenu(state) {
            state.activeMenu = state.activeMenu !== true;
        },
        activeModal(state) {
            state.activeTeamModal = state.activeTeamModal !== true;
        },
        activeModalBoard(state) {
            state.activePopup = false;
            state.activeMenu = false;
            state.activeArchive = false;
            state.activeBoardModal = state.activeBoardModal !== true;
        },
        activePopup(state) {
            state.activePopup = state.activePopup !== true;
        },
        activateInvitation(state) {
            state.activePopup = false;
            state.activeMenu = false;
            state.activeArchive = false;
            state.activeInvitation = state.activeInvitation !== true;
        },
        activateArchive(state) {
            state.activePopup = false;
            state.activeMenu = false;
            state.activeArchive = state.activeArchive !== true;
        },
        activateCardList(state) {
            state.activeCardList = state.activeCardList !== true;
        }
    },
    actions: {},
    modules: {}
})
