<template>
  <div class="form-section">
    <div v-if="isEditMode">
	    <my-project-path
			department="전체목록"
			project="오픈소스 수정하기"
			rootPath="/list/opensource"
		  />
		  <h1 class="oss-detail-title">{{ opensource.name }}</h1>
    </div>
    <div v-else class="title">
      <h2>새로운 OpenSource 추가</h2>
    </div>
    <form action="" class="form">
      <p class="field required">
        <label class="label required" for="name">오픈소스 이름</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          placeholder="오픈소스 이름"
          v-model="opensource.name"
        />
      </p>
      <p class="field required">
        <label class="label" for="url">주소</label>
        <input
          class="text-input"
          id="url"
          name="url"
          required
          type="url"
          placeholder="주소"
          v-model="opensource.url"
        />
      </p>
      <div class="tooltip-url"><span>!</span>
        <p class="tooltip-url-content">Source URL 또는 Homepage URL을 입력해주세요</p>
      </div>
      <p class="field required">
        <label class="label required" for="copyright">저작권</label>
        <input
          class="text-input"
          id="copyright"
          name="copyright"
          required
          type="text"
          placeholder="저작권"
          v-model="opensource.copyright"
        />
      </p>
      <div class="tooltip-copyright"><span>!</span>
        <p class="tooltip-copyright-content">Copyright이 확인되지 않을 경우, property of respective owner를 입력해주세요</p>
      </div>
      <p class="field">
        <label class="label required" for="version">버전</label>
        <input
          class="text-input"
          id="version"
          name="version"
          required
          type="text"
          placeholder="버전"
          v-model="opensource.version"
        />
      </p>
      <p class="field">
        <label class="label required" for="package_type">패키지 타입</label>
        <input
          class="text-input"
          id="package_type"
          name="package_type"
          required
          type="text"
          placeholder="패키지 타입"
          v-model="opensource.packageType"
        />
      </p>
      <p class="field required">
        <label class="label required" for="artifact_id">아티팩트ID</label>
        <input
          class="text-input"
          id="artifact_id"
          name="artifact_id"
          required
          type="text"
          placeholder="아티팩트ID"
          v-model="opensource.artifactId"
        />
      </p>
      <div class="tooltip-artifact"><span>!</span>
        <p class="tooltip-artifact-content">버전 정보를 생략한 jar 파일의 이름</p>
      </div>
      <p class="field">
        <label class="label required" for="group_id">그룹ID</label>
        <input
          class="text-input"
          id="group_id"
          name="group_id"
          required
          type="text"
          placeholder="그룹ID"
          v-model="opensource.groupId"
        />
      </p>
      <div class="tooltip-group"><span>!</span>
        <p class="tooltip-group-content">프로젝트를 식별하는 유일한 값</p>
      </div>
      <p class="field required" v-on:keyup.down="selectValue('down')" v-on:keyup.up="selectValue('up')">
        <label class="label required" for="enter_license">라이선스</label>
        <input
          class="text-input-l"

          id="enter_license"
          name="enter_license"
          required
          type="text"
          placeholder="라이선스"
          v-model="licenseName"
        >
        <ul class="license-watch-ul" :class="{show:searched}" tabindex="0">
        <li tabindex="-1" 
          v-for="(licenseInfoName, index) in licenses"
          :key="index"
          @click="selectTag(licenseInfoName.name,licenseInfoName.licenseId)"
          v-on:keyup.enter="selectValue('enter',licenseInfoName.name,licenseInfoName.licenseId)"
        >
          <span>{{ licenseInfoName.name }}</span>
        </li>
        <li class="add-license" @click="licenseAddPage">➕add license</li>
      </ul>
      </p>
      
      <div class="tag-input">
        <div
          v-for="(tag, index) in tags"
          :key="tag"
          class="tag-input__tag"
        >
          <span class="tags" @click="removeTag(index)">x</span>
          {{ tag }}
        </div>
      </div>
    </form>

    
    <div class="add-component-button-div">
      <div v-if="isEditMode">
        <span class="btn edit-btn" :class="{CantDo:!CanDo}" @click="updateOpenSource">수정</span>
        <span class="btn delete-btn" @click="deleteOpenSource">삭제</span>
      </div>
      <div v-else >
        <span class="btn" :class="{CantDo:!CanDo}" @click="addOpenSource">추가</span>
      </div>
    </div>
    
  </div>
</template>
<script>
import MyProjectPath from "@/components/MyProject/MyProjectPath.vue";
import opensourceApi from "@/api/opensource.js";
import swal from "@/api/alert.js";
export default {
  name: "AddComponent",
  components:{
    MyProjectPath,
  },
  data() {
    return {
      opensource: {
        name: "",
        url: "",
        copyright: "",
        version: "",
        packageType: "",
        artifactId: "",
        groupId:"",
        licenseId:[],
      },
      tags: [],
      licenses: [],
      licenseName: "",
      searched: false,
      isEmpty:false,
      isEditMode:false,
    };
  },
  created() {
    if(window.location.pathname == '/list/detail/editOpensource'){
      //수정하기 모드
      let editOpensource = this.$route.params.editOpensource;
      // let licenseList= this.$route.params.licenseList;
      if(editOpensource){
        this.isEditMode = true;
        this.opensource = editOpensource;
        this.opensource.licenseId = [];
        for(let license of editOpensource.licenseList){
          this.opensource.licenseId.push(license.licenseId);
          this.tags.push(license.name);
        }
        delete this.opensource.licenseList;
      }
      else{
        this.$router.go(-1);
      }
    }
    else{
      let unmapped = this.$route.params.unmappedOpensource;
      if(unmapped){
        this.opensource.artifactId = unmapped.artifactId;
        this.opensource.groupId = unmapped.groupId;
        this.opensource.version = unmapped.version;
      }
    }
  },
 
  watch: {
    licenseName: function (newVal) {
      this.getLicenseName(newVal);
    },
  },
  computed:{
    CanDo:function(){
      //전부 입력되었는지 확인
      if(this.opensource.name.length > 0 &&
      this.opensource.url.length > 0 &&
      this.opensource.copyright.length > 0 &&
      this.opensource.artifactId.length > 0 &&
      this.opensource.licenseId.length > 0){
          return true;
      }
      return false;
    },
  },
  methods: {
    licenseAddPage(){
      this.$router.push({ name: "MyProjectAddLicense", query:this.$route.query });
    },
    getLicenseName(newVal) {
      opensourceApi.getLicenseName(newVal).then((response) => {
        this.licenses = response.data;
        var str = this.licenseName;
        if(str!==''){
          this.searched=true;
        }else{
          this.searched=false;
        }
      });
    },
    selectTag(name,id) {
      var val = name.trim();
      if (val.length > 0) {
        if(!this.tags.includes(val)){
          this.tags.push(val);
        this.opensource.licenseId.push(id);
        }
      }
    },
    removeTag(index) {
      this.tags.splice(index, 1);
      this.opensource.licenseId.splice(index,1);
    },
    addOpenSource() {
      //오픈소스 추가
      if(this.CanDo){
          opensourceApi.addOpenSource(this.opensource).then(()=>{
            swal.success('오픈소스 정보가 추가되었습니다.');
            this.$router.go(-1);
          });
      }
    },
    updateOpenSource(){
      //오픈소스 수정
      if(this.CanDo){
        opensourceApi.updateOpenSource(this.opensource).then(()=>{
          swal.success('오픈소스 정보가 수정되었습니다.');
          this.$router.go(-1);
        });
      }
    },
    deleteOpenSource(){
      //오픈소스 삭제
      if(this.CanDo){
        swal.confirm('정말로 삭제하시겠습니까?').then((response) => {
          if(response.value){
             opensourceApi.deleteOpenSource(this.opensource.opensourceId).then(()=>{
              swal.success('오픈소스 정보가 삭제되었습니다.');
              this.$router.go(-2);
            });
          }
        })
      }
    },
    selectValue(keycode,str,id){
      if(this.searched === true){
        const hasClass = document.querySelector('.license-watch-ul').classList.contains('key');
        if(keycode === 'down'){
          if(!hasClass){
            const thisEl = document.querySelectorAll('.license-watch-ul li')[0];
            document.querySelector('.license-watch-ul').classList.add('key');
            thisEl.classList.add('sel');
            thisEl.focus();
          }
          else {
            const lastEl = document.querySelector('.license-watch-ul li:last-child');
            const thisEl = document.querySelector('.license-watch-ul li.sel');
            const nextEl = thisEl.nextElementSibling;
            if (!lastEl.classList.contains('sel')) {
              thisEl.classList.remove('sel');
              nextEl.classList.add('sel');
              nextEl.focus();
            }
          }
        }
        if (keycode === 'up' && hasClass) {
          const firstEl = document.querySelectorAll('.license-watch-ul li')[0];
          const thisEl = document.querySelector('.license-watch-ul li.sel');
          const prevEl = thisEl.previousElementSibling;
          if (!firstEl.classList.contains('sel')) {
            thisEl.classList.remove('sel');
            prevEl.classList.add('sel');
            prevEl.focus();
          } else {
            document.querySelector('.text-input-l').focus();
          }
        }
        if (keycode === 'enter' && hasClass) {
          this.selectTag(str,id);
          const thisEl = document.querySelector('.text-input-l');
          thisEl.focus();
        }
      }
    },
  },
};
</script>
<style scoped src="../../assets/css/MyProject/AddComponent.css"></style>
