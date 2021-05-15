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

      <label class="label">라이선스 주요내용</label>
      <p class="field">
        <label class="sublabel">OSI 승인여부</label>
        <input
          name="filter_osi"
          id="filter_osi1"
          type="radio"
          :value="true"
          v-model="license.is_osi"
        />
        <label class="radiobtn" for="filter_osi1"> O </label>
        <input
          name="filter_osi"
          id="filter_osi2"
          type="radio"
          :value="false"
          v-model="license.is_osi"
        />
        <label class="radiobtn" for="filter_osi2"> X </label>
        <input
          name="filter_osi"
          id="filter_osi3"
          type="radio"
          :value="null"
          v-model="license.is_osi"
        />
        <label class="radiobtn" for="filter_osi3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel">복제, 배포, 수정의 권한 허용</label>
        <input
          name="filter_modify"
          id="filter_modify1"
          type="radio"
          :value="true"
          v-model="license.is_modify"
        />
        <label class="radiobtn" for="filter_modify1"> O </label>
        <input
          name="filter_modify"
          id="filter_modify2"
          type="radio"
          :value="false"
          v-model="license.is_modify"
        />
        <label class="radiobtn" for="filter_modify2"> X </label>
        <input
          name="filter_modify"
          id="filter_modify3"
          type="radio"
          :value="null"
          v-model="license.is_modify"
        />
        <label class="radiobtn" for="filter_modify3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel">배포시 라이선스 사본 첨부</label>
        <input
          name="filter_license"
          id="filter_license1"
          type="radio"
          :value="true"
          v-model="license.is_license"
        />
        <label class="radiobtn" for="filter_license1"> O </label>
        <input
          name="filter_license"
          id="filter_license2"
          type="radio"
          :value="false"
          v-model="license.is_license"
        />
        <label class="radiobtn" for="filter_license2"> X </label>
        <input
          name="filter_license"
          id="filter_license3"
          type="radio"
          :value="null"
          v-model="license.is_license"
        />
        <label class="radiobtn" for="filter_license3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo"
          >저작권 고지사항 또는 Attribution 고지사항 유지</label
        >
        <input
          name="filter_copyright"
          id="filter_copyright1"
          type="radio"
          :value="true"
          v-model="license.is_copyright"
        />
        <label class="radiobtn" for="filter_copyright1"> O </label>
        <input
          name="filter_copyright"
          id="filter_copyright2"
          type="radio"
          :value="false"
          v-model="license.is_copyright"
        />
        <label class="radiobtn" for="filter_copyright2"> X </label>
        <input
          name="filter_copyright"
          id="filter_copyright3"
          type="radio"
          :value="null"
          v-model="license.is_copyright"
        />
        <label class="radiobtn" for="filter_copyright3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel" for="licenseinfo"
          >조합저작물 작성 및 타 라이선스 배포 허용</label
        >
        <input
          name="filter_anotherli"
          id="filter_anotherli1"
          type="radio"
          :value="true"
          v-model="license.is_anotherlicense"
        />
        <label class="radiobtn" for="filter_anotherli1"> O </label>
        <input
          name="filter_anotherli"
          id="filter_anotherli2"
          type="radio"
          :value="false"
          v-model="license.is_anotherlicense"
        />
        <label class="radiobtn" for="filter_anotherli2"> X </label>
        <input
          name="filter_anotherli"
          id="filter_anotherli3"
          type="radio"
          :value="null"
          v-model="license.is_anotherlicense"
        />
        <label class="radiobtn" for="filter_anotherli3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel">수정 시 수정내용 고지</label>
        <input
          name="filter_modify_notice"
          id="filter_modify_notice1"
          type="radio"
          :value="true"
          v-model="license.is_modify_notice"
        />
        <label class="radiobtn" for="filter_modify_notice1"> O </label>
        <input
          name="filter_modify_notice"
          id="filter_modify_notice2"
          type="radio"
          :value="false"
          v-model="license.is_modify_notice"
        />
        <label class="radiobtn" for="filter_modify_notice2"> X </label>
        <input
          name="filter_modify_notice"
          id="filter_modify_notice3"
          type="radio"
          :value="null"
          v-model="license.is_modify_notice"
        />
        <label class="radiobtn" for="filter_modify_notice3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel">보증의 부인</label>
        <input
          name="filter_warranty"
          id="filter_warranty1"
          type="radio"
          :value="true"
          v-model="license.is_warranty"
        />
        <label class="radiobtn" for="filter_warranty1"> O </label>
        <input
          name="filter_warranty"
          id="filter_warranty2"
          type="radio"
          :value="false"
          v-model="license.is_warranty"
        />
        <label class="radiobtn" for="filter_warranty2"> X </label>
        <input
          name="filter_warranty"
          id="filter_warranty3"
          type="radio"
          :value="null"
          v-model="license.is_warranty"
        />
        <label class="radiobtn" for="filter_warranty3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel">책임의 제한</label>
        <input
          name="filter_respon"
          id="filter_respon1"
          type="radio"
          :value="true"
          v-model="license.is_respon"
        />
        <label class="radiobtn" for="filter_respon1"> O </label>
        <input
          name="filter_respon"
          id="filter_respon2"
          type="radio"
          :value="false"
          v-model="license.is_respon"
        />
        <label class="radiobtn" for="filter_respon2"> X </label>
        <input
          name="filter_respon"
          id="filter_respon3"
          type="radio"
          :value="null"
          v-model="license.is_respon"
        />
        <label class="radiobtn" for="filter_respon3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel">상업적 사용 가능</label>
        <input
          name="filter_commercial"
          id="filter_commercial1"
          type="radio"
          :value="true"
          v-model="license.is_commercial"
        />
        <label class="radiobtn" for="filter_commercial1"> O </label>
        <input
          name="filter_commercial"
          id="filter_commercial2"
          type="radio"
          :value="false"
          v-model="license.is_commercial"
        />
        <label class="radiobtn" for="filter_commercial2"> X </label>
        <input
          name="filter_commercial"
          id="filter_commercial3"
          type="radio"
          :value="null"
          v-model="license.is_commercial"
        />
        <label class="radiobtn" for="filter_commercial3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel">동일한 라이선스 유지</label>
        <input
          name="filter_continue"
          id="filter_continue1"
          type="radio"
          :value="true"
          v-model="license.is_continue"
        />
        <label class="radiobtn" for="filter_continue1"> O </label>
        <input
          name="filter_continue"
          id="filter_continue2"
          type="radio"
          :value="false"
          v-model="license.is_continue"
        />
        <label class="radiobtn" for="filter_continue2"> X </label>
        <input
          name="filter_continue"
          id="filter_continue3"
          type="radio"
          :value="null"
          v-model="license.is_continue"
        />
        <label class="radiobtn" for="filter_continue3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel">특허권 사용 여부</label>
        <input
          name="filter_patent"
          id="filter_patent1"
          type="radio"
          :value="true"
          v-model="license.is_patent"
        />
        <label class="radiobtn" for="filter_patent1"> O </label>
        <input
          name="filter_patent"
          id="filter_patent2"
          type="radio"
          :value="false"
          v-model="license.is_patent"
        />
        <label class="radiobtn" for="filter_patent2"> X </label>
        <input
          name="filter_patent"
          id="filter_patent3"
          type="radio"
          :value="null"
          v-model="license.is_patent"
        />
        <label class="radiobtn" for="filter_patent3"> 내용없음 </label>
      </p>

      <p class="field">
        <label class="sublabel">비공개 사용 여부</label>
        <input
          name="filter_private"
          id="filter_private1"
          type="radio"
          :value="true"
          v-model="license.is_private"
        />
        <label class="radiobtn" for="filter_private1"> O </label>
        <input
          name="filter_private"
          id="filter_private2"
          type="radio"
          :value="false"
          v-model="license.is_private"
        />
        <label class="radiobtn" for="filter_private2"> X </label>
        <input
          name="filter_private"
          id="filter_private3"
          type="radio"
          :value="null"
          v-model="license.is_private"
        />
        <label class="radiobtn" for="filter_private3"> 내용없음 </label>
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
        <span @click="addLicense">추가</span>
      </div>
    </div>
  </div>
</template>

<script>
// import '@/assets/css/MyProject/MyProjectAddLicense.scss';
import licenseApi from '@/api/license.js';

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
      licenseinfo: '',
    };
  },
  watch: {
    // licenseinfo: function () {
    //   console.log(this.licenseinfo);
    // },
  },
  methods: {
    addLicense() {
      licenseApi.addLicense(this.license).then(() => {
        console.log('추가성공');
        this.$router.go(-1);
      });
    },
  },
};
</script>
<style
  lang="scss"
  scoped
  src="../../assets/css/MyProject/MyProjectAddLicense.scss"
></style>
