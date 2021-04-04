import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/views/Login";
import Home from "@/views/Home";
import Registration from "@/views/Registration";
import NotFound from "@/views/NotFound";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: '/login', component: Login},
        {path: '/', component: Home},
        {path: '/registration', component: Registration},
        {path: '/:notFound(.*)', component: NotFound}
    ]
})

export default router
