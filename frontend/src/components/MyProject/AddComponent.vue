<template>
  <div class="form-section">
    <div class="title">
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
        <label class="label" for="email">URL</label>
        <input
          class="text-input"
          id="url"
          name="url"
          required
          type="url"
          placeholder="URL"
          v-model="opensource.url"
        />
      </p>
      <div class="tooltip-url"><span>!</span>
        <p class="tooltip-url-content">Source URL 또는 Homepage URL을 입력해주세요</p>
      </div>
      <p class="field required">
        <label class="label required" for="name">Copyright</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          placeholder="Copyright"
          v-model="opensource.copyright"
        />
      </p>
      <div class="tooltip-copyright"><span>!</span>
        <p class="tooltip-copyright-content">Copyright이 확인되지 않을 경우, property of respective owner를 입력해주세요</p>
      </div>
      <p class="field">
        <label class="label required" for="name">Version</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          placeholder="Version"
          v-model="opensource.version"
        />
      </p>
      <p class="field">
        <label class="label required" for="name">Package Type</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          placeholder="Package Type"
          v-model="opensource.packagetype"
        />
      </p>
      <p class="field required">
        <label class="label required" for="name">Artifact Id</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          placeholder="Artifact Id"
          v-model="opensource.artifactId"
        />
      </p>
      <div class="tooltip-artifact"><span>!</span>
        <p class="tooltip-artifact-content">버전 정보를 생략한 jar 파일의 이름</p>
      </div>
      <p class="field">
        <label class="label required" for="name">Group Id</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          placeholder="Group Id"
          v-model="opensource.groupId"
        />
      </p>
      <div class="tooltip-group"><span>!</span>
        <p class="tooltip-group-content">프로젝트를 식별하는 유일한 값</p>
      </div>
      <p class="field required" v-on:keyup.down="selectValue('down')" v-on:keyup.up="selectValue('up')">
        <label class="label required" for="name">License</label>
        <input
          class="text-input-l"

          id="name"
          name="name"
          required
          type="text"
          placeholder="Enter License"
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
      </ul>
      </p>
      
      <div class="tag-input">
        <div
          v-for="(tag, index) in opensource.tags"
          :key="tag"
          class="tag-input__tag"
        >
          <span class="tags" @click="removeTag(index)">x</span>
          {{ tag }}
        </div>
      </div>
    </form>

    <div class="box-3">
      <div class="btn btn-three">
        <span class="add" @click="addOpenSource">추가</span>
      </div>
    </div>
  </div>
</template>
<script>
import licenseApi from "@/api/opensource.js";
export default {
  name: "AddComponent",
  data() {
    return {
      opensource: {
        name: "",
        url: "",
        copyright: "",
        version: "",
        packagetype: "",
        artifactId: "",
        tags: [],
        licenseId:[],
      },
      licenses: [],
      licenseName: "",
      searched: false,
    };
  },
  watch: {
    licenseName: function (newVal) {
      this.getLicenseName(newVal);
    },
  },
  methods: {
    getLicenseName(newVal) {
      licenseApi.getLicenseName(newVal).then((response) => {
        console.log(response.data);
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
      console.log(name)
      console.log(id)
      var val = name.trim();
      if (val.length > 0) {
        this.opensource.tags.push(val);
        this.opensource.licenseId.push(id);
      }
    },
    removeTag(index) {
      this.opensource.tags.splice(index, 1);
    },
    addOpenSource() {
      licenseApi.addComponent(this.opensource).then(()=>{
          alert('Opensource추가 완료')
      })
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
