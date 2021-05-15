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
          v-model="license.name"
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
          v-model="license.identifier"
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
          v-model="license.url"
        />
      </p>
      <div class="tooltip-url">
        <span>!</span>
        <p class="tooltip-url-content">
          Source URL 또는 Homepage URL을 입력해주세요
        </p>
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
          v-model="license.contents"
        />
      </p>
      <div class="tooltip-contents">
        <span>!</span>
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
          v-model="license.sourceopen"
        />
      </p>

      <label class="label" for="licenseinfo">라이선스 주요내용</label>
      <p class="field">
        <label class="sublabel" for="licenseinfo">OSI 승인여부</label>
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_osi"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_osi"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_osi"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo"
          >복제, 배포, 수정의 권한 허용</label
        >
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_modify"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_modify"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_modify"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo"
          >배포시 라이선스 사본 첨부</label
        >
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_license"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_license"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_license"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo"
          >저작권 고지사항 또는 Attribution 고지사항 유지</label
        >
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_copyright"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_copyright"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_copyright"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo"
          >조합저작물 작성 및 타 라이선스 배포 허용</label
        >
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_anotherlicense"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_anotherlicense"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_anotherlicense"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo">수정 시 수정내용 고지</label>
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_modify_notice"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_modify_notice"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_modify_notice"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo">보증의 부인</label>
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_warranty"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_warranty"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_warranty"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo">책임의 제한</label>
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_respon"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_respon"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_respon"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo">상업적 사용 가능</label>
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_commercial"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_commercial"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_commercial"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo">동일한 라이선스 유지</label>
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_continue"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_continue"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_continue"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo">특허권 사용 여부</label>
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_patent"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_patent"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_patent"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo">비공개 사용 여부</label>
        <input
          name="filter"
          id="filter1"
          type="radio"
          :value="true"
          v-model="license.is_private"
        />
        <label class="radiobtn" for="filter1">
          <!-- <span class="material-icons"> check_circle_outline </span> -->
          O
        </label>
        <input
          name="filter"
          id="filter2"
          type="radio"
          :value="false"
          v-model="license.is_private"
        />
        <label class="radiobtn" for="filter2"> X </label>
        <input
          name="filter"
          id="filter3"
          type="radio"
          :value="null"
          v-model="license.is_private"
        />
        <label class="radiobtn" for="filter3"> 내용없음 </label>
      </p>

      <!-- <p class="field required">
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

          <label for="codeopen2">네트워크 서비스 포함 전체코드 공개</label>
          <label for="codeopen3">표준버전</label>
          <label for="codeopen4">전체코드 공개</label>
          <label for="codeopen5">2차저작물 공개</label>
          <label for="codeopen6">파일단위 공개</label>
          <label for="codeopen7">파일단위 2차 저작물 공개</label>
          <label for="codeopen8">동봉 SW 공개</label>

        </ul>
      </p> -->
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
    return {
      license: {
        name: '',
        url: '',
        identifier: '',
        contents: '',
        sourceopen: '',
        is_osi: '',
        is_modify: '',
        is_license: '',
        is_copyright: '',
        is_anotherlicense: '',
        is_modify_notice: '',
        is_warranty: '',
        is_respon: '',
        is_commercial: '',
        is_continue: '',
        is_patent: '',
        is_private: '',
      },
    };
  },
  watch: {
    // licenseinfo: function () {
    //   console.log(this.licenseinfo);
    // },
  },
  methods: {},
};
</script>
<style
  lang="scss"
  scoped
  src="../../assets/css/MyProject/MyProjectAddLicense.scss"
></style>
