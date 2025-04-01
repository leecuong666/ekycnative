import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import nativeCalc from './specs/NativeCalc'

const App = () => {
  return (
    <View style={styles.container}>
      <Text>{nativeCalc.add(1, 2)}</Text>
      <Text>{nativeCalc.except(2, 3)}</Text>
      <Text>{nativeCalc.multiply(3, 4)}</Text>
      <Text>{nativeCalc.divide(4, 5)}</Text>
    </View>
  )
}

export default App

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  }
})