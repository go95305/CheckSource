<template>
  <form action="">
    <!--  General -->
    <div class="form-group">
      <h2 class="heading">라이선스 추가</h2>
      <div class="controls">
        <input type="text" id="name" class="floatLabel" name="name" />
        <label for="name">Name</label>
      </div>
      <div class="controls">
        <input type="email" id="email" class="floatLabel" name="email" />
        <label for="email">Email</label>
      </div>
      <div class="controls">
        <i class="fa fa-sort"></i>
        <select class="floatLabel">
          <option value="blank"></option>
          <option value="Apple">Apple</option>
          <option value="Banana">Banana</option>
          <option value="Kiwi">Kiwi</option>
          <option value="Orange">Orange</option>
          <option value="Watermelon">Watermelon</option>
        </select>
        <label for="fruit">Favotire Fruit</label>
      </div>
      <div class="controls">
        <textarea name="comments" class="floatLabel" id="comments"></textarea>
        <label for="comments">Comments</label>
        <button>Submit</button>
      </div>
    </div>
  </form>
</template>

<script>
import '@/assets/css/MyProject/MyProjectAddLicense.scss';

export default {
  name: 'MyProjectAddLicense',
  data() {
    return {};
  },
  methods: {},
};
</script>
