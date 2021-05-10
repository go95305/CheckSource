<template>
  <form action="">
    <div class="form-group">
      <h2 class="heading">라이선스 추가</h2>
      <div class="controls">
        <input type="text" id="name" class="floatLabel" name="name" />
        <label class="form-control-placeholder" for="name">Full name</label>
      </div>
      <div class="controls">
        <input
          type="text"
          id="identifier"
          class="floatLabel"
          name="identifier"
        />
        <label class="form-control-placeholder" for="identifier"
          >Identifier</label
        >
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
        <label class="form-control-placeholder" for="fruit">Risk</label>
      </div>
      <div class="controls">
        <textarea name="notice" class="floatLabel" id="notice"></textarea>
        <label class="form-control-placeholder" for="notice">Notice</label>
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
