<template>
  <div class="main__content--right">
    <div class="main__content--group">
      <div class="content__group--title">
        <svg class="symbol__personal">
          <use xlink:href="#personal"></use>
        </svg>
        <span class="content__group--text">Персональные доски</span>
      </div>
      <div class="content__group--cards">
        <a href="#" class="content__group--card notext-decoration">
          Best coders table
        </a>
        <a href="#" class="content__group--card notext-decoration content__group_create__card">
          Создать доску
        </a>
      </div>
    </div>
    <div class="main__content--group">
      <div class="content__group--title">
        <svg class="symbol__personal">
          <use xlink:href="#group"></use>
        </svg>
        <span class="content__group--text">Персональные доски</span>
      </div>
      <div class="content__group--cards">
        <a href="#" class="content__group--card notext-decoration">
          Best coders table
        </a>
        <a href="#" class="content__group--card notext-decoration content__group_create__card">
          Создать доску
        </a>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppMainContentLeft"
}
</script>

<style scoped>

</style>