<template>
  <app-sprite></app-sprite>
  <section class="main__section">
    <app-header-main></app-header-main>
    <app-content-main></app-content-main>
  </section>
  <app-member-menu></app-member-menu>
  <app-create-team></app-create-team>
  <app-create-board></app-create-board>
</template>

<script>
import AppSprite from "@/sprite/AppSprite";
import AppHeaderMain from "@/components/Main/AppHeaderMain";
import AppMemberMenu from "@/components/Main/AppMemberMenu";
import AppContentMain from "@/components/Main/AppContentMain";
import AppCreateTeam from "@/components/Main/AppCreateTeam";
import AppCreateBoard from "@/components/Main/AppCreateBoard";

export default {
  name: "Main",
  components: {AppSprite, AppHeaderMain, AppMemberMenu, AppContentMain, AppCreateTeam,AppCreateBoard}
}
</script>

<style scoped>

</style>