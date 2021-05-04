<template>
  <div class="form-section">
    <div class="title">
      <h2>새로운 Component 추가</h2>
    </div>
    <div class="dependency-title">
      <h3>Dependency</h3>
    </div>
    <form class="dependency">
      <div class="path">
        <label class="dependency-label">path</label>
        <input v-model="dependency.path" type="text" placeholder="path" />
      </div>
      <div class="version">
        <label class="dependency-label">version</label>
        <input v-model="dependency.version" type="text" placeholder="version" />
      </div>
      <div class="complianceUrl">
        <label class="dependency-label">ComplianceUrl</label>
        <input
          v-model="dependency.complianceUrl"
          type="text"
          placeholder="ComplianceUrl"
        />
      </div>
    </form>
    <div class="component-title">
      <h3>Component 생성</h3>
    </div>
    <form class="component">
      <div class="name">
        <label class="component-label">name</label>
        <input v-model="component.name" type="text" placeholder="name" />
      </div>
      <div class="url">
        <label class="component-label">url</label>
        <input v-model="component.url" type="text" placeholder="url" />
      </div>
      <div class="License">
        <label class="component-label">License</label>
        <input v-model="component.license" type="text" placeholder="License" />
      </div>
    </form>
    <button @click="addComponent">추가</button>
  </div>
</template>
<script>
export default {
  name: "AddComponent",
  data() {
    return {
      dependency: {
        path: "",
        version: "",
        complianceUrl: "",
      },
      component: {
        name: "",
        url: "",
        license: "",
      },
    };
  },
  methods: {
    addComponent() {
      alert(this.dependency.path);
    },
  },
};
</script>
<style scoped src="../../assets/css/MyProject/AddComponent.css"></style>
