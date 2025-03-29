<script setup lang="ts">
import type { ProductInfo } from "@/api/products";
import { ref, watchEffect } from "vue";

const isEditProduct = defineModel<boolean>({ required: true });
const props = withDefaults(
  defineProps<{
    product: ProductInfo;
    handleSubmit: (product: ProductInfo) => void;
    handleDelete?: (id: string) => void;
  }>(),
  {}
);
const emits = defineEmits<{ (event: "updateProduct", product: ProductInfo): void }>();

const product = ref(props.product);
watchEffect(() => {
  emits("updateProduct", product.value);
});

const handleSubmit = props.handleSubmit;
</script>

<template>
  <ElForm label-width="auto">
    <ElFormItem label="ID">
      <ElInput v-model="product.id" disabled />
    </ElFormItem>
    <ElFormItem label="标题">
      <ElInput v-model="product.title" :disabled="!isEditProduct" />
    </ElFormItem>
    <ElFormItem label="价格">
      <ElInput v-model="product.price" :disabled="!isEditProduct" />
    </ElFormItem>
    <ElFormItem label="比率">
      <ElInput v-model="product.rate" :disabled="!isEditProduct" />
    </ElFormItem>
    <ElFormItem label="描述">
      <ElInput v-model="product.description" :disabled="!isEditProduct" />
    </ElFormItem>
    <ElFormItem label="概述">
      <ElInput v-model="product.cover" :disabled="!isEditProduct" />
    </ElFormItem>
    <ElFormItem label="详细信息">
      <ElInput v-model="product.detail" :disabled="!isEditProduct" />
    </ElFormItem>
    <ElFormItem label="规格">
      <ElFormItem v-for="spec in product.specifications" :key="spec.id" v-model="spec.value">
        <ElFormItem label="ID">
          <ElInput v-model="spec.id" disabled />
        </ElFormItem>
        <ElFormItem label="键">
          <ElInput v-model="spec.item" :disabled="!isEditProduct" />
        </ElFormItem>
        <ElFormItem label="值">
          <ElInput v-model="spec.value" :disabled="!isEditProduct" />
        </ElFormItem>
        <ElFormItem label="产品ID">
          <ElInput v-model="spec.productId" :disabled="!isEditProduct" />
        </ElFormItem>
        <ElButton :disabled="!isEditProduct">删除</ElButton>
        <ElDivider />
        <ElFormItem>
          <ElButton :disabled="!isEditProduct"> 新建 </ElButton>
        </ElFormItem>
      </ElFormItem>
    </ElFormItem>
    <ElFormItem>
      <ElButton v-if="!isEditProduct" @click="isEditProduct = !isEditProduct">解锁</ElButton>
      <ElButton v-else @click="isEditProduct = !isEditProduct">锁定</ElButton>
      <ElButton v-show="isEditProduct" @click="() => handleSubmit(product)">提交</ElButton>
      <ElButton
        v-show="handleDelete !== undefined"
        type="danger"
        :disabled="!isEditProduct"
        @click="() => handleDelete && handleDelete(product.id)"
        >删除</ElButton
      >
    </ElFormItem>
  </ElForm>
</template>
