<template>
  <div class="form-section">
    <div class="title">
      <h2>새로운 Component 추가</h2>
    </div>
    <div class="dependency-title">
      <h3>Dependency</h3>
    </div>
    <form action="" class="form">
      <p class="field required">
        <label class="label required" for="name">path</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          placeholder="입력"
        />
      </p>
      <p class="field required half">
        <label class="label" for="email">version</label>
        <input
          class="text-input"
          id="email"
          name="email"
          required
          type="email"
        />
      </p>
      <p class="field half">
        <label class="label" for="phone">ComplianceUrl</label>
        <input class="text-input" id="phone" name="phone" type="phone" />
      </p>
    </form>

    <div class="component-title">
      <h3>Component 생성</h3>
    </div>
    <form action="" class="form">
      <p class="field required">
        <label class="label required" for="name">path</label>
        <input
          class="text-input"
          id="name"
          name="name"
          required
          type="text"
          value="path 입력"
        />
      </p>
      <p class="field required half">
        <label class="label" for="email">version</label>
        <input
          class="text-input"
          id="email"
          name="email"
          required
          type="email"
        />
      </p>
      <p class="field half">
        <label class="label" for="phone">ComplianceUrl</label>
        <input class="text-input" id="phone" name="phone" type="phone" />
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
