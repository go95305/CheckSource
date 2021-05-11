<template>
  <div class="form-section">
    <div class="title">
      <h2>새로운 License 추가</h2>
    </div>
    <form action="" class="form">
      <p class="field required">
        <label class="label required" for="lname">라이선스 이름</label>
        <input
          class="text-input"
          id="lname"
          name="lname"
          required
          type="text"
          placeholder="라이선스 이름"
        />
      </p>
      <p class="field required">
        <label class="label" for="identifier">Identifier</label>
        <input
          class="text-input"
          id="identifier"
          name="identifier"
          required
          type="text"
          placeholder="Identifier"
        />
      </p>
      <p class="field required">
        <label class="label" for="url">URL</label>
        <input
          class="text-input"
          id="url"
          name="url"
          required
          type="text"
          placeholder="URL"
        />
      </p>
      <p class="field required">
        <label class="label required" for="contents">라이선스 전문</label>
        <textarea
          rows="8"
          class="text-input"
          id="contents"
          name="contents"
          required
          type="text"
          placeholder="라이선스 전문"
        />
      </p>
      <!-- <p class="field required">
        <label class="label required" for="name">Package Type</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          placeholder="package type"
        />
      </p> -->

      <p class="field required">
        <label class="label required" for="name">Package Type</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          placeholder="package type"
        />
      </p>
    </form>

    <div class="box-3">
      <div class="btn btn-three">
        <span>추가</span>
      </div>
    </div>
  </div>
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
