<template>
  <select
    @change="$emit('input', $event.target.value)"
    class="form-select form-select-sm">
    <option :value="null" selected>--</option>
    <option value="HOME_WIN">Home team wins (1)</option>
    <option value="DRAW">Draw (x)</option>
    <option value="AWAY_WIN">Away team wins (2)</option>
  </select>
</template>

