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
      <div class="tooltip-url"><span>!</span>
        <p class="tooltip-url-content">Source URL 또는 Homepage URL을 입력해주세요</p>
      </div>
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
      <div class="tooltip-contents"><span>!</span>
        <p class="tooltip-contents-content">라이선스 전문을 입력해주세요</p>
      </div>
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
        <label class="label required" for="sourceopen">소스코드 공개여부</label>
        <input
          class="text-input"
          id="sourceopen"
          name="sourceopen"
          required
          type="text"
          placeholder="소스코드 공개여부"
        />
      </p>


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

          <li class="add-l-check">
            <input
              class="styled-checkbox"
              id="codeopen4"
              type="checkbox"
              value="co3"
            />
            <label for="codeopen4">전체코드 공개</label>
          </li>

          <li class="add-l-check">
            <input
              class="styled-checkbox"
              id="codeopen5"
              type="checkbox"
              value="co5"
            />
            <label for="codeopen5">2차저작물 공개</label>
          </li>

          <li class="add-l-check">
            <input
              class="styled-checkbox"
              id="codeopen6"
              type="checkbox"
              value="co6"
            />
            <label for="codeopen6">파일단위 공개</label>
          </li>

          <li class="add-l-check">
            <input
              class="styled-checkbox"
              id="codeopen7"
              type="checkbox"
              value="co7"
            />
            <label for="codeopen7">파일단위 2차 저작물 공개</label>
          </li>

          <li class="add-l-check">
            <input
              class="styled-checkbox"
              id="codeopen8"
              type="checkbox"
              value="co8"
            />
            <label for="codeopen8">동봉 SW 공개</label>
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
// import '@/assets/css/MyProject/MyProjectAddLicense.scss';

export default {
  name: 'MyProjectAddLicense',
  data() {
    return {};
  },
  methods: {},
};
</script>
<style lang="scss" scoped src="../../assets/css/MyProject/MyProjectAddLicense.scss"></style>