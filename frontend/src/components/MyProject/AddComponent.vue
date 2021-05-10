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
        />
      </p>
      <p class="field required">
        <label class="label" for="email">URL</label>
        <input
          class="text-input"
          id="email"
          name="email"
          required
          type="email"
          placeholder="URL"
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
          placeholder="package type"
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
        />
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
