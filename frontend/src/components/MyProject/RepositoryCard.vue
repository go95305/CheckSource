<template>
    <div class="repository-card">
        <h1>{{ repository.name }}</h1>
    </div>
</template>
<script>
export default {
    name: "RepositoryCard",
    props: {
        repository: Object,
    },
};
</script>
<style scoped src="@/assets/css/MyProject/RepositoryCard.css"></style>
