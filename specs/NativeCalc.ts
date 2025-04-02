import type {TurboModule} from 'react-native';
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  add(val1: number, val2: number): number;
  except(val1: number, val2: number): number;
  multiply(val1: number, val2: number): number;
  divide(val1: number, val2: number): number;
  startEkyc: (
    accessToken: string,
    tokenId: string,
    tokenKey: string,
  ) => Promise<String>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('NativeCalc');
