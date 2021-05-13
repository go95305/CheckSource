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
      <p class="field required">
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
      <p class="field required">
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
      <p class="field required">
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
      <p class="field required">
        <label class="label required" for="name">License</label>
        <input
          class="text-input"
          @keydown.enter="addTag"
          @keydown.188="addTag"
          id="name"
          name="name"
          required
          type="text"
          placeholder="Enter License"
          v-model="licenseName"
        >
        <ul class="license-watch-ul" v-bind:class="{show:searched}">
        <li
          class="license-watch"
          v-for="(licenseInfoName, index) in licenses"
          :key="index"
          @click="selectTag(licenseInfoName.name,licenseInfoName.licenseId)"
        >
          {{ licenseInfoName.name }}
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
        if (newVal == "") {
          this.licenses = [];
          this.searched = false;
        } else if (newVal != "") {
          this.searched = true;
        }
      });
    },
    addComponent() {
      alert(this.dependency.path);
    },
    addTag(event) {
      event.preventDefault();
      console.log(event.target.value);
      var val = event.target.value.trim();
      if (val.length > 0) {
        this.opensource.tags.push(val);
        event.target.value = "";
        this.licenseName = "";
      }
    },
    selectTag(name,id) {
      var val = name.trim();
      if (val.length > 0) {
        this.searched = false;
        this.opensource.tags.push(val);
        this.opensource.tagId.push(id);
        this.licenseName = "";
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
  },
};
</script>
<style scoped src="../../assets/css/MyProject/AddComponent.css"></style>
