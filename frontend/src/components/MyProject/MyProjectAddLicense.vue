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
        <label class="label required">소스코드 공개여부</label>
        <ul class="unstyled centered">
          <li class="add-l-check">
            <input
              class="styled-checkbox"
              id="codeopen1"
              type="checkbox"
              value="co1"
            />
            <label for="codeopen1">모듈 단위 공개</label>
          </li>
          <li class="add-l-check">
            <input
              class="styled-checkbox"
              id="codeopen2"
              type="checkbox"
              value="co2"
            />
            <label for="codeopen2">네트워크 서비스 포함 전체코드 공개</label>
          </li>
          <li class="add-l-check">
            <input
              class="styled-checkbox"
              id="codeopen3"
              type="checkbox"
              value="co3"
            />
            <label for="codeopen3">표준버전</label>
          </li>
        </ul>
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
