<template>
    <div class="myproject-edit-gitlab">
        <repository-card
            v-for="(repository, index) in repositoryList"
            :key="`${index}_repositoryList`"
            :repository="repository"
            :choiced="false"
            :added="true"
            @addRepoClick="AddRepoClick(index)"
        ></repository-card>
    </div>
</template>
<script>
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
export default {
    components: { RepositoryCard },
    name: "MyProjectEditGitLab",
    data() {
        return {
            repositoryList: [
                {
                    name: "A",
                },
                {
                    name: "B",
                },
                {
                    name: "C",
                },
                {
                    name: "D",
                },
                {
                    name: "E",
                },
                {
                    name: "F",
                },
                {
                    name: "A",
                },
                {
                    name: "B",
                },
                {
                    name: "C",
                },
                {
                    name: "F",
                },
            ],
        };
    },
    methods: {
        AddRepoClick: function (index) {
            this.$emit("addRepoClick", this.repositoryList[index]);
        },
    },
};
</script>
<style scoped src="@/assets/css/MyProject/Edit/MyProjectEditGitLab.css"></style>
