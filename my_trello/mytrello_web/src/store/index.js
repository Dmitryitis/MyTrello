import {createStore} from 'vuex'
import auth from './modules/auth.module'
import main from "@/store/modules/main.module";
import board from './modules/board.module'

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
            activeCard: false,
            user: {},
            popupUser: {
                email: '',
                username: '',
                surname: '',
            },
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
        activePopup(state, user) {
            state.activePopup = state.activePopup !== true;
            state.popupUser = user
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
        },
        activateCard(state) {
            state.activePopup = false;
            state.activeMenu = false;
            state.activeArchive = false;
            state.activeCard = state.activeCard !== true;
        }
    },
    getters: {
        popupUser(state) {
            return state.popupUser;
        }
    },
    actions: {},
    modules: {
        auth, main, board
    }
})
