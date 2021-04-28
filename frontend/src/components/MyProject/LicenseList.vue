<template>
  <div class="container">
    <div class="table-header">
      <div class="title title-1">라이센스명</div>
      <div class="title title-2">주소</div>
      <div class="title title-3">고지 의무</div>
    </div>
    <ul class="responsive-table" :key="index" v-for="(item, index) in list">
      <li class="table-row" :class="{ illegal: list[index].illegal }">
        <div class="col col-1">{{ item.name }}</div>
        <div class="col col-2">{{ item.url }}</div>
        <div class="col col-3">{{ item.obligation }}</div>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  data() {
    return {
      list: [
        {
          name: "Apache License 2.0",
          url: "http://www.apache.org/licenses/LICENSE-2.0",
          obligation: "Apache License 2.0",
          illegal: true,
        },
        {
          name: "GNU General Public License v2.0 or later",
          url: "https://opensource.org/licenses/BSD-3-Clause",
          obligation: "Apache License 2.0",
          illegal: true,
        },
        {
          name: "Eclipse Public License 2.0",
          url: "https://www.eclipse.org/legal/epl-2.0",
          obligation: "Apache License 2.0",
          illegal: false,
        },
      ],
    };
  },
};
</script>
<style scoped src="../../assets/css/MyProject/LicenseList.css"></style>
