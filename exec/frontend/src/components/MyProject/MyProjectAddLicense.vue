<template>
  <div class="form-section">
    <div v-if="isEditMode">
      <my-project-path
        department="전체목록"
        project="라이선스 수정하기"
        rootPath="/list/license"
      />
      <h1 class="oss-detail-title">{{ license.name }}</h1>
    </div>
    <div v-else class="title">
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
          type="text"
          placeholder="라이선스 전문"
          v-model="license.contents"
        />
      </p>
      <div class="tooltip-contents">
        <span>!</span>
        <p class="tooltip-contents-content">라이선스 전문을 입력해주세요</p>
      </div>

      <p class="field">
        <label class="label" for="sourceopen">소스코드 공개여부</label>
        <input
          class="text-input"
          id="sourceopen"
          name="sourceopen"
          required
          type="text"
          placeholder="공개 의무가 없으면 비워주세요"
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
    </form>

    <div class="add-component-button-div">
      <div v-if="isEditMode">
        <span
          class="btn edit-btn"
          :class="{ CantDo: !CanDo }"
          @click="updateLicense"
          >수정</span
        >
        <span class="btn delete-btn" @click="deleteLicense">삭제</span>
      </div>
      <div v-else>
        <span class="btn" :class="{ CantDo: !CanDo }" @click="addLicense"
          >추가</span
        >
      </div>
    </div>
    <!-- <div class="box-3">
			<div class="btn btn-three">
			
			</div>
		</div> -->
  </div>
</template>

<script>
// import '@/assets/css/MyProject/MyProjectAddLicense.scss';
import licenseApi from '@/api/license.js';
import MyProjectPath from '@/components/MyProject/MyProjectPath.vue';
import swal from '@/api/alert.js';
export default {
  name: 'MyProjectAddLicense',
  components: {
    MyProjectPath,
  },
  data() {
    return {
      license: {
        name: '',
        url: '',
        identifier: '',
        contents: '',
        sourceopen: '',
        is_osi: null,
        is_modify: null,
        is_license: null,
        is_copyright: null,
        is_anotherlicense: null,
        is_modify_notice: null,
        is_warranty: null,
        is_respon: null,
        // is_commercial: null,
        // is_continue: null,
        // is_patent: null,
        // is_private: null,
      },
      licenseinfo: '',
      isEditMode: false,
    };
  },
  props: {
    editLicense: Object,
  },
  created() {
    if (window.location.pathname == '/list/detail/editLicense') {
      //수정하기 모드
      if (this.editLicense) {
        this.isEditMode = true;
        this.license = this.editLicense;
        this.license.contents = window.atob(this.license.contents);
      } else {
        this.$router.go(-1);
      }
    }
  },
  computed: {
    CanDo: function () {
      //전부 입력되었는지 확인
      if (
        this.license.name.length > 0 &&
        this.license.url.length > 0 &&
        this.license.contents.length > 0 &&
        this.license.identifier.length > 0
      ) {
        return true;
      }
      return false;
    },
  },
  methods: {
    addLicense() {
      if (this.CanDo) {
        this.license.contents = window.btoa(this.license.contents);
        licenseApi.addLicense(this.license).then(() => {
          swal.success('라이선스가 추가되었습니다.');
          this.$router.go(-1);
        });
      }
    },
    updateLicense() {
      if (this.CanDo) {
        this.license.contents = window.btoa(this.license.contents);
        licenseApi.updateLicense(this.license).then(() => {
          swal.success('라이선스가 수정되었습니다.');
          this.$router.go(-1);
        });
      }
    },
    deleteLicense() {
      swal.confirm('정말로 삭제하시겠습니까?').then((response) => {
        if (response.value) {
          licenseApi.deleteLicense(this.license.licenseId).then(() => {
            swal.success('라이선스가 삭제되었습니다');
            this.$router.go(-2);
          });
        }
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
